package home.database;

import home.crafting.Recipe;
import home.crafting.CraftingRecipes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
public class ListingDbSqlImpl implements ListingsDb {

    @Autowired
    JdbcTemplate jdbcTemplate;

    List<Recipe> recipes;

    public ListingDbSqlImpl(JdbcTemplate jdbcTemplate) throws SQLException {
        this.jdbcTemplate = jdbcTemplate;
//        createSchema();
        loadDataFromFiles();
        recipes = CraftingRecipes.getAll();
    }

    private void createSchema() {
        jdbcTemplate.execute("USE cabbagereport");
        System.out.println("Dropping table cabbagereport...");
        jdbcTemplate.execute("DROP TABLE listings");
        System.out.println("Creating new table...");
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
        jdbcTemplate.execute("USE cabbagereport");
        int current = 1;
        Map<Long, List<Listing>> listings = JsonToPojosUtil.loadFromAHDB();
        List<Long> timeStamps = getTimestamps();
        for (Map.Entry<Long, List<Listing>> entry : listings.entrySet()) {
            if (!timeStamps.contains(entry.getKey())) {
                System.out.println("Uploading scan " + current + " of " + listings.entrySet().size());
                addListings(entry.getValue());
            } else {
                System.out.println("Skipping scan " + current + " of " + listings.entrySet().size());
            }
            current ++;

        }
    }

    @Override
    public int[] addListings(List<Listing> listings) {
        List<Object[]> jsonListings = new LinkedList<>();
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

        return result;
    }

    public List<Long> getTimestamps() {
        jdbcTemplate.execute("USE cabbagereport");
        return jdbcTemplate.query(
                "SELECT DISTINCT date FROM listings",
                (rs, rowNum) -> rs.getLong("date")
        );
    }

    @Override
    public String[] getItemNames() {
        jdbcTemplate.execute("USE cabbagereport");
        List<String> names = jdbcTemplate.query(
                "SELECT DISTINCT itemName FROM listings",
                (rs, rowNum) -> rs.getString("itemName")
        );

        return Arrays.copyOf(names.toArray(), names.size(), String[].class);
    }

    @Override
    public Double getCurrentPrice(String itemName) {
        return getPriceForTimestamp(itemName, getLatestTimeStamp(itemName));
    }

    @Override
    public Integer getLatestQuantity(String itemName) {
        return jdbcTemplate.queryForObject("SELECT SUM(numPerStack) " +
                        "FROM listings " +
                        "WHERE date = ? AND " +
                        "itemName = ?",
                new Object[]{getLatestTimeStamp(itemName), itemName},
                Integer.class);
    }

    @Override
    public Long[][] getPricesForInterval(String itemName, long l) {
        List<Long[]> prices = jdbcTemplate.query(
                "SELECT date, MIN(unitBuyout) " +
                "FROM listings " +
                "WHERE " +
                "itemname = ? " +
                "AND date >= ? " +
                "GROUP BY date " +
                "ORDER BY date",
                new Object[]{itemName, l},
                new ArrayRowMapper());

        Long[][] result = new Long[prices.size()][2];
        for (Long[] entry : prices) {
            result[prices.indexOf(entry)] = entry;
        }
        return result;
    }

    @Override
    public Long[][] getQuantitiesForInterval(String itemName, long l) {
        List<Long[]> quantities = jdbcTemplate.query(
                "SELECT date, SUM(numPerStack) " +
                        "FROM listings " +
                        "WHERE " +
                        "itemname = ? " +
                        "AND date >= ? " +
                        "GROUP BY date " +
                        "ORDER BY date",
                new Object[]{itemName, l},
                new QuantityRowMapper());

        Long[][] result = new Long[quantities.size()][2];
        for (Long[] entry : quantities) {
            result[quantities.indexOf(entry)] = entry;
        }
        return result;
    }

    @Override
    public Map<String, Integer> getSellers(String itemName) {
        List<Object[]> entries = jdbcTemplate.query(
                "SELECT userName, SUM(numPerStack) " +
                "FROM listings " +
                "WHERE itemName=? " +
                "AND date = ? " +
                "GROUP BY userName " +
                "ORDER BY SUM(numPerStack)",
                new Object[]{itemName, getLatestTimeStamp(itemName)},
                new EntryRowmapper());

        Map<String, Integer> result = new TreeMap<>();
        for (Object[] entry : entries) {
            result.put((String) entry[0], (Integer) entry[1]);
        }
        return result;
    }

    private Double getPriceForTimestamp(String name, Long lastTs) {
        jdbcTemplate.execute("USE cabbagereport");
        return jdbcTemplate.queryForObject("SELECT MIN(unitBuyout) " +
                        "FROM listings " +
                        "WHERE date = ? AND " +
                        "itemName = ?",
                new Object[]{lastTs, name},
                Double.class)/10000;
    }

    private Long getLatestTimeStamp(String name) {
        return jdbcTemplate.queryForObject("SELECT MAX(date) " +
                "FROM listings " +
                "WHERE itemName = ?",
                new Object[]{name},
                Long.class);

    }

    private class ArrayRowMapper implements RowMapper<Long[]> {
        @Override
        public Long[] mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Long[]{rs.getLong("date"), rs.getLong("MIN(unitBuyout)")};
        }
    }

    private class QuantityRowMapper implements RowMapper<Long[]> {
        @Override
        public Long[] mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Long[]{rs.getLong("date"), rs.getLong("SUM(numPerStack)")};
        }
    }

    private class EntryRowmapper implements RowMapper<Object[]> {
        @Override
        public Object[] mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Object[]{rs.getString("userName"), rs.getInt("SUM(NumPerStack)")};
        }
    }
}
