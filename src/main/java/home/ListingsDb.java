package home;

import org.json.simple.JSONObject;

public interface ListingsDb {
    void addDataFromJson(JSONObject jsonData);
    ItemPageData getItemPageData(String name);
    Double getLatestPrice(String name);
}