package home.database;

import home.crafting.Profession;
import home.crafting.ProfitEntry;
import home.crafting.Recipe;
import home.crafting.CraftingRecipes;
import home.view.HCUtil;
import home.view.ItemPageData;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.*;

@Repository
public class ListingDbSqlImpl implements ListingsDb {

    @Autowired
    JdbcTemplate jdbcTemplate;

    List<Recipe> recipes;

    public ListingDbSqlImpl(JdbcTemplate jdbcTemplate) throws SQLException {
        this.jdbcTemplate = jdbcTemplate;
        createSchema();
        loadDataFromFiles();
        recipes = CraftingRecipes.getAll();
    }

    private void createSchema() {
        jdbcTemplate.execute("USE cabbagereport");
        jdbcTemplate.execute("DROP TABLE listings");
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
        int current = 1;
        Map<Long, List<Listing>> listings = JsonToPojosUtil.loadFromAHDB();
        for (Map.Entry<Long, List<Listing>> entry : listings.entrySet()) {
            System.out.println("Uploading scan " + current + " of " + listings.entrySet().size());
            addListings(entry.getValue());
            current ++;
        }
    }

    @Override
    public int[] addListings(List<Listing> listings) {
        List<Object[]> jsonListings = new LinkedList<>();
        System.out.println("Creating list...");
        for (Listing listing : listings) {
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
        int[] argTypes = new int[8];
        argTypes[0] = Types.VARCHAR;
        argTypes[1] = Types.VARCHAR;
        argTypes[2] = Types.INTEGER;
        argTypes[3] = Types.INTEGER;
        argTypes[4] = Types.INTEGER;
        argTypes[5] = Types.INTEGER;
        argTypes[6] = Types.INTEGER;
        argTypes[7] = Types.BIGINT;
        System.out.println("Calling batchupdate...");
        int result[] = jdbcTemplate.batchUpdate(
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
                jsonListings,
                argTypes
        );
        System.out.println("Finished");

        return result;
    }

    @Override
    public void addDataFromJson(JSONObject jsonData) {

    }

    @Override
    public List<ProfitEntry> getProfitEntries(Profession profession) {
        List<ProfitEntry> entries = new LinkedList<>();

        for (Recipe recipe : recipes) {
            entries.add(getProfitEntry(recipe));
        }

        return entries;
    }

    public ProfitEntry getProfitEntry(Recipe recipe) {
        Double rawProfit = getTotalPrice(recipe.getProducts());
        Double costToCraft = getTotalPrice(recipe.getComponents());
        Double ahCut = round(rawProfit * .05, 4);
        Double totalProfit = round(rawProfit - costToCraft - ahCut, 4);

        ProfitEntry profitEntry = new ProfitEntry();
        profitEntry.setName(recipe.getName());
        profitEntry.setComponents(recipe.getComponents());
        profitEntry.setSalePrice(rawProfit);
        profitEntry.setCraftingPrice(costToCraft);
        profitEntry.setAhCut(ahCut);
        profitEntry.setTotalprofit(totalProfit);

        return profitEntry;
    }

    private Double getTotalPrice(Map<String, Integer> components) {
        Double total = new Double(0);
        for (Map.Entry<String, Integer> entry : components.entrySet()) {
            total += (getLatestPrice(entry.getKey()) * entry.getValue());
        }
        return total;
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

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
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
