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
        for (Listing listing : JsonToPojosUtil.jsonToListing(jsonData)) {
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
        List<Listing> weeklyListings = getListingsForInterval(name, 604800000l);
        List<Listing> monthlyListings = getListingsForInterval(name, 2419200000l);
        Long[][] weeklyQuantity = HCUtil.listToQuantityArray(weeklyListings);
        itemPageData.setItemName(name);
        itemPageData.setPrice(getLatestPrice(name));
        itemPageData.setQuantity(weeklyQuantity[weeklyQuantity.length-1][1].intValue());
        itemPageData.setWeeklyPrice(HCUtil.listToPriceArray(weeklyListings));
        itemPageData.setWeeklyQuantity(HCUtil.listToQuantityArray(weeklyListings));
        itemPageData.setMonthlyPrice(HCUtil.listToPriceArray(monthlyListings));
        itemPageData.setMonthlyQuantity(HCUtil.listToQuantityArray(monthlyListings));
        itemPageData.setWeeklySellers(getLatestSellers(name));
        itemPageData.setMonthlySellers(getLatestSellers(name));
        return itemPageData;
    }

    @Override
    public Double getLatestPrice(String name) {
        return getPriceForTimestamp(name, getLatestTimeStamp(name));
    }

    private Map<String, Integer> getLatestSellers(String name) {
        Long lastTs = getLatestTimeStamp(name);
        List<Listing> listings = getListingsForTimeStamp(name, lastTs);
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

        return HCUtil.sortByValue(sellers);

    }

    private List<Listing> getListingsForTimeStamp(String name, Long lastTs) {
        return jdbcTemplate.query(
                "SELECT * FROM listings " +
                        "WHERE " +
                        "itemName = ? AND " +
                        "date = ?",
                new Object[]{name, lastTs},
                new ListingRowMapper()
        );
    }

    private Double getPriceForTimestamp(String name, Long lastTs) {
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
}
