package home;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ListingSqlImpl implements ListingsDb {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public ListingSqlImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        createSchema();
        loadDataFromFiles();
    }

    private void createSchema() {
        jdbcTemplate.execute("DROP TABLE listings IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE listings " +
                "(id SERIAL," +
                "itemName VARCHAR(255), " +
                "userName VARCHAR(255), " +
                "numStacks INT, " +
                "numPerStack INT, " +
                "totalBid INT, " +
                "totalBuyout INT, " +
                "unitBuyout INT, " +
                "date BIGINT)");
    }

    private void loadDataFromFiles() {
        JSONObject jsonObject;
        JSONParser jsonParser = new JSONParser();

        List<Path> files = new LinkedList<>();

        try {
            files = Files.list(Paths.get("data")).map(Path::getFileName).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Path path : files) {
            try {
                FileReader reader = new FileReader("data/" + path.toString());
                jsonObject = (JSONObject) jsonParser.parse(reader);
                reader.close();
            } catch (Exception e) {
                jsonObject = new JSONObject();
                e.printStackTrace();
            }
            addDataFromJson(jsonObject);
        }
    }

    @Override
    public void addDataFromJson(JSONObject jsonData) {
        List<Object[]> jsonListings = new LinkedList<>();
        for (Listing listing : JsonToPojosUtil.jsonToListing((JSONObject) jsonData.get("AuctionDBSaved"))) {
            jsonListings.add(new Object[]{
                    listing.getItemName(),
                    listing.getUserName(),
                    listing.getNumStacks(),
                    listing.getNumPerStack(),
                    listing.getTotalBid(),
                    listing.getTotalBuyout(),
                    listing.getUnitBuyout(),
                    listing.getDate()
            });
        }
        jdbcTemplate.batchUpdate(
                "INSERT INTO listings(" +
                        "itemName," +
                        "userName," +
                        "numStacks," +
                        "numPerStack," +
                        "totalBid," +
                        "TotalBuyout," +
                        "unitBuyout," +
                        "date) " +
                        "VALUES (?,?,?,?,?,?,?,?)",
                jsonListings
        );

    }

    @Override
    public ItemPageData getItemPageData(String name) {
        ItemPageData itemPageData = new ItemPageData();
        Long lastTs = getLatestTimeStamp(name);
        List<Listing> weeklyListings = getListingsForInterval(name, 604800000l);
        List<Listing> monthlyListings = getListingsForInterval(name, 2419200000l);
        Long[][] weeklyQuantity = getQuantityArray(weeklyListings);
        itemPageData.setItemName(name);
        itemPageData.setPrice(getPrice(name, lastTs));
        itemPageData.setQuantity(weeklyQuantity[weeklyQuantity.length-1][1].intValue());
        itemPageData.setWeeklyPrice(getPriceArray(weeklyListings));
        itemPageData.setWeeklyQuantity(getQuantityArray(weeklyListings));
        itemPageData.setMonthlyPrice(getPriceArray(monthlyListings));
        itemPageData.setMonthlyQuantity(getQuantityArray(monthlyListings));
        itemPageData.setWeeklySellers(getLatestSellers(name));
        itemPageData.setMonthlySellers(getLatestSellers(name));
        return itemPageData;
    }

    private Map<String, Integer> getLatestSellers(String name) {
        Long lastTs = getLatestTimeStamp(name);
        List<Listing> listings = jdbcTemplate.query(
                "SELECT * FROM listings " +
                        "WHERE " +
                        "itemName = ? AND " +
                        "date = ?",
                new Object[]{name, lastTs},
                new ListingRowMapper()
        );
        Map<String, Integer> sellers = new HashMap<>();
        for (Listing listing : listings) {
            String user = listing.getUserName();
            Integer quantity = listing.getNumStacks() * listing.getNumPerStack();
            if (sellers.get(user) == null) {
                sellers.put(user, quantity);
            } else {
                Integer newQuantity = sellers.get(user) + quantity;
                sellers.put(user, newQuantity);
            }
        }

        return sortByValue(sellers);


    }

    private Double getPrice(String name, Long lastTs) {
        Integer unitBuyout = jdbcTemplate.queryForObject("SELECT MIN(unitBuyout) " +
                        "FROM listings " +
                        "WHERE date = ? AND " +
                        "itemName = ?",
                new Object[]{lastTs, name},
                Integer.class);

        return unitBuyout.doubleValue()/10000;
    }

    private Long getLatestTimeStamp(String name) {
        return jdbcTemplate.queryForObject("SELECT MAX(date) " +
                "FROM listings " +
                "WHERE itemName = ?",
                new Object[]{name},
                Long.class);

    }

    private List<Listing> getListingsForInterval(String name, long l) {
        Long startingTs = System.currentTimeMillis() - l;
        return jdbcTemplate.query(
                "SELECT * FROM listings " +
                        "WHERE " +
                        "itemName = ? AND " +
                        "date >= ?",
                new Object[]{name, startingTs},
                new ListingRowMapper()
        );
    }

    private Long[][] getPriceArray(List<Listing> listings) {
        Map<Long, Integer> result = new HashMap<>();
        for (Listing listing : listings) {
            if (result.get(listing.getDate())==null) {
                result.put(listing.getDate(), listing.getUnitBuyout());
            } else if (listing.getUnitBuyout() < result.get(listing.getDate())) {
                result.put(listing.getDate(), listing.getUnitBuyout());
            }
        }

        return mapToArray(result);
    }

    private Long[][] getQuantityArray(List<Listing> listings) {
        Map<Long, Integer> result = new HashMap<>();
        for (Listing listing : listings) {
            Integer currentTotal = result.get(listing.getDate());
            Integer itemsInListing = listing.getNumPerStack()* listing.getNumStacks();
            if (currentTotal==null) {
                result.put(listing.getDate(), itemsInListing);
            } else {
                Integer newTotal = currentTotal + (itemsInListing);
                result.put(listing.getDate(), newTotal);
            }
        }

        return mapToArray(result);
    }

    //turns a map into an array of longs, sorted by key.

    private Long[][] mapToArray(Map<Long, Integer> result) {
        Long[][] outArray = new Long[result.keySet().size()][2];
        int count = 0;
        List<Long> list = new ArrayList<>(result.keySet());
        Collections.sort(list);
        for (Long ts : list) {
            outArray[count][0] = ts;
            outArray[count][1] = Long.valueOf(result.get(ts));
            count++;
        }
        return outArray;
    }


    private class ListingRowMapper implements RowMapper<Listing> {


        @Override
        public Listing mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Listing(
                    rs.getString("itemName"),
                    rs.getString("userName"),
                    rs.getInt("numStacks"),
                    rs.getInt("numPerStack"),
                    rs.getInt("totalBid"),
                    rs.getInt("totalBuyout"),
                    rs.getInt("unitBuyout"),
                    rs.getLong("date")
            );
        }
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        System.out.println(result);
        return result;
    }
}
