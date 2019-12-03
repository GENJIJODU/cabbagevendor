package home;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ListingSqlImpl implements ListingsDb {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public ListingSqlImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        createSchema();
    }

    private void createSchema() {
        jdbcTemplate.execute("DROP TABLE cards IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE cards " +
                "(id SERIAL," +
                "itemName VARCHAR(255), " +
                "userName VARCHAR(255), " +
                "numStacks INT, " +
                "numPerStack INT, " +
                "totalBid INT, " +
                "totalBuyout INT, " +
                "unitBuyout INT, " +
                "date TIMESTAMP)");
    }

    @Override
    public void addDataFromJson(JSONObject jsonData) {
        
    }

    @Override
    public List<Listing> getAllListings() {
        return null;
    }

    @Override
    public ItemPageData getItemPageData(String name) {
        return null;
    }

    @Override
    public List<Listing> getListingsByName(String itemName) {
        return null;
    }

    @Override
    public List<Listing> getListingByUser(String userName) {
        return null;
    }
}
