package home.database;

import home.view.ItemPageData;
import home.crafting.Profession;
import home.crafting.ProfitEntry;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

public interface ListingsDb {
    int[] addListings(List<Listing> listings);
    String[] getItemNames();

    Double getCurrentPrice(String itemName);

    Integer getLatestQuantity(String itemName);

    Long[][] getPricesForInterval(String itemName, long l);

    Long[][] getQuantitiesForInterval(String itemName, long l);

    Map<String, Integer> getSellers(String itemName);
}