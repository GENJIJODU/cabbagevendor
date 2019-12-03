package home;

import org.json.simple.JSONObject;

import java.util.List;

public interface ListingsDb {
    void addDataFromJson(JSONObject jsonData);
    ItemPageData getItemPageData(String name);
}