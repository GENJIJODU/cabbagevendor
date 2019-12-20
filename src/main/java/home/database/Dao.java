package home.database;

import home.crafting.CraftingRecipes;
import home.crafting.Profession;
import home.crafting.ProfitEntry;
import home.crafting.Recipe;
import home.view.ItemPageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class Dao {

    ListingsDb listingsDb;

    @Autowired
    public Dao(ListingsDb listingsDb) {
        this.listingsDb = listingsDb;
    }
    public Object getItemPageData(String itemName) {
        ItemPageData itemPageData = new ItemPageData();
        itemPageData.setItemName(itemName);
        itemPageData.setPrice(listingsDb.getCurrentPrice(itemName));
        itemPageData.setQuantity(listingsDb.getLatestQuantity(itemName));
        itemPageData.setWeeklyPrice(listingsDb.getPricesForInterval(itemName, System.currentTimeMillis() - 604800000l));
        itemPageData.setWeeklyQuantity(listingsDb.getQuantitiesForInterval(itemName, System.currentTimeMillis() - 604800000l));
        itemPageData.setMonthlyPrice(listingsDb.getPricesForInterval(itemName, System.currentTimeMillis() - 2419200000l));
        itemPageData.setMonthlyQuantity(listingsDb.getQuantitiesForInterval(itemName, System.currentTimeMillis() - 2419200000l));
        itemPageData.setWeeklySellers(listingsDb.getSellers(itemName));
        itemPageData.setMonthlySellers(listingsDb.getSellers(itemName));
        return itemPageData;
    }

    public Object getItemNames() {
        return listingsDb.getItemNames();
    }

    public Object getProfitEntries(Profession profession) {
        List<ProfitEntry> entries = new LinkedList<>();

        for (Recipe recipe : CraftingRecipes.getAll()) {
            entries.add(getProfitEntry(recipe));
        }

        return entries;
    }

    public ProfitEntry getProfitEntry(Recipe recipe) {
        Double productValue = getTotalPrice(recipe.getProducts());
        Double costToCraft = getTotalPrice(recipe.getComponents());
        Double ahCut = round(productValue * .05, 4);
        Double totalProfit = round(productValue - costToCraft - ahCut, 4);

        ProfitEntry profitEntry = new ProfitEntry();
        profitEntry.setName(recipe.getName());
        profitEntry.setComponents(recipe.getComponents());
        profitEntry.setSalePrice(productValue);
        profitEntry.setCraftingPrice(costToCraft);
        profitEntry.setAhCut(ahCut);
        profitEntry.setTotalprofit(totalProfit);

        return profitEntry;
    }

    private Double getTotalPrice(Map<String, Integer> items) {
        Double total = new Double(0);
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            total += (listingsDb.getCurrentPrice(entry.getKey()) * entry.getValue());
        }
        return total;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
