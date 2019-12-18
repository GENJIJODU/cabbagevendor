package home.database;

import home.view.ItemPageData;
import home.crafting.Profession;
import home.crafting.ProfitEntry;
import org.json.simple.JSONObject;

import java.util.List;

public interface ListingsDb {
    void addDataFromJson(JSONObject jsonData);
    ItemPageData getItemPageData(String name);
    int[] addListings(List<Listing> listings);
    List<ProfitEntry> getProfitEntries(Profession profession);
    Double getLatestPrice(String name);
}