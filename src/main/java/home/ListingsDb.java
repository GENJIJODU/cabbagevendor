package home;

import org.json.simple.JSONObject;

import java.util.List;

public interface ListingsDb {
    void addDataFromJson(JSONObject jsonData);
    List<Listing> getAllListings();
    ItemPageData getItemPageData(String name);
    List<Listing> getListingsByName(String itemName);
    List<Listing> getListingByUser(String userName);
}