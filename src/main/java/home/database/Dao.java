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
import java.util.*;

@Service
public class Dao {

    Long latestScan = 0l;
    ListingsDb listingsDb;
    Map<String, ItemPageData> itemCache = new HashMap<>();
    Map<String, List<ProfitEntry>> professionCache = new HashMap<>();
    String[] itemNamesCache;

    @Autowired
    public Dao(ListingsDb listingsDb) {
        this.listingsDb = listingsDb;
    }

    public ItemPageData getItemPageData(String itemName) {
        if(!itemCache.containsKey(itemName) || databaseHasUpdated()) {
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

            itemCache.put(itemName, itemPageData);
            return itemPageData;
        } else {
            System.out.println("returning cached page data....");
            return itemCache.get(itemName);
        }
    }

    public String[] getItemNames() {
        if (itemNamesCache == null || databaseHasUpdated()) {
            itemNamesCache = listingsDb.getItemNames();
            updateLatestTimeStamp();
        } else {
            System.out.println("returning cached itemnames....");
        }

        return itemNamesCache;
    }

    public Map<String, Map<String, Double>> getHerbalismMap() {
        return getGatherablePriceMaps(CraftingRecipes.allHerbs(), CraftingRecipes.herbMap());
    }

    public Map<String, Map<String, Double>> getSkinningMap() {
        return getGatherablePriceMaps(CraftingRecipes.allSkinning(), CraftingRecipes.skinningMap());
    }

    public Map<String, Map<String, Double>> getMiningMap() {
        return getGatherablePriceMaps(CraftingRecipes.allMining(), CraftingRecipes.miningMap());
    }

    public Map<String, Map<String, Double>> getGatherablePriceMaps(Set<String> recipes, Map<String, List<String>> categories) {
        Map<String, Map<String, Double>> categoryMap = new TreeMap<>();
        Map<String, Double> allPrices = listingsDb.getPrices(recipes);
        for (Map.Entry<String, List<String>> category : categories.entrySet()) {
            categoryMap.put(category.getKey(), new TreeMap<>());
            for (String gatherable : category.getValue()) {
                if (allPrices.get(gatherable)!=null)
                    categoryMap.get(category.getKey()).put(gatherable, round(allPrices.get(gatherable)/10000, 4));
            }
        }
        sortByPrice(categoryMap);
        return categoryMap;
    }

    private void sortByPrice(Map<String, Map<String, Double>> categoryMap) {
        for (String category : categoryMap.keySet()) {
            categoryMap.put(category, sortMapByValue(categoryMap.get(category)));
        }
    }
    public static Map<String, Double> sortMapByValue(Map<String, Double> map){
        Map result = sortByValues(map);
        return result;
    }

    public Map<String, List<ProfitEntry>> getAlchemyMap() {
        return getProfitEntries(CraftingRecipes.getAllAlchemy(), CraftingRecipes.getAlchemyMap());
    }

    public Map<String, List<ProfitEntry>> getCookingMap() {
        return getProfitEntries(CraftingRecipes.getAllCooking(), CraftingRecipes.getCookingMapped());
    }

    public Map<String, List<ProfitEntry>> getEngineeringMap() {
        return getProfitEntries(CraftingRecipes.getAllEngineering(), CraftingRecipes.getEngineeringMapped());
    }

    private Map<String, List<ProfitEntry>> getProfitEntries(List<Recipe> allCooking, Map<String, List<Recipe>> cookingMapped) {
        if (professionCache.isEmpty()) {
            Map<String, List<Recipe>> mappedRecipes = cookingMapped;
            Map<String, Double> allItemPrices = getAllItemPricesForRecipes(allCooking);
            Map<String, List<ProfitEntry>> entries = new TreeMap<>();

            for (Map.Entry<String, List<Recipe>> entry : mappedRecipes.entrySet()) {
                entries.put(entry.getKey(), new ArrayList<>());
                for (Recipe recipe : entry.getValue()) {
                    Set<String> availableItems = allItemPrices.keySet();
                    if (dataExistsForRecipe(recipe, availableItems)) {
                        ProfitEntry profitEntry = getEntry(recipe, allItemPrices);
                        entries.get(entry.getKey()).add(profitEntry);
                    } else {
                        System.out.println("Skipping recipe " + recipe.getName());
                    }
                }
            }
            sortByMostProfit(entries);
            professionCache = entries;
            updateLatestTimeStamp();
            return entries;
        } else {
            System.out.println("returning cached profession data....");
            return professionCache;
        }
    }

    public static <K, V extends Comparable<V>> Map<K, V>
    sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator =
                (k1, k2) -> {
                    int compare =
                            map.get(k2).compareTo(map.get(k1));
                    if (compare == 0)
                        return 1;
                    else
                        return compare;
                };

        Map<K, V> sortedByValues =
                new TreeMap<>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }

    private void sortByMostProfit(Map<String, List<ProfitEntry>> entries) {
        for (String key : entries.keySet()) {
            entries.get(key).sort((o1, o2) -> o2.getTotalprofit().compareTo(o1.getTotalprofit()));
        }

    }


    private static boolean dataExistsForRecipe(Recipe recipe, Set<String> availableItems) {
        return availableItems.containsAll(recipe.getComponents().keySet()) &&
                availableItems.containsAll(recipe.getProducts().keySet());
    }

    private ProfitEntry getEntry(Recipe recipe, Map<String, Double> allItemPrices) {
        Double productValue = getPriceForItems(recipe.getProducts(), allItemPrices);
        Double costToCraft = getPriceForItems(recipe.getComponents(), allItemPrices);
        Double ahCut = round(productValue * .05, 4);
        Double totalProfit = round(productValue - costToCraft - ahCut, 4);

        ProfitEntry profitEntry = new ProfitEntry();
        profitEntry.setName(recipe.getName());
        profitEntry.setComponents(recipe.getComponents());
        profitEntry.setSalePrice(productValue);
        profitEntry.setCraftingPrice(costToCraft);
        profitEntry.setAhCut(ahCut);
        profitEntry.setTotalprofit(totalProfit);
        profitEntry.products = recipe.getProducts();
        return profitEntry;
    }

    private Double getPriceForItems(Map<String, Integer> products, Map<String, Double> allItemPrices) {
        Double total = Double.valueOf(0);
        for (Map.Entry<String, Integer> entry : products.entrySet()) {
            total += allItemPrices.get(entry.getKey()) * entry.getValue();
        }
        return round(total/10000, 4);
    }

    private Map<String, Double> getAllItemPricesForRecipes(List<Recipe> recipes) {
        Set<String> items = new HashSet<>();
        for (Recipe recipe : recipes) {
            items.addAll(recipe.getComponents().keySet());
            items.addAll(recipe.getProducts().keySet());
        }
        return listingsDb.getPrices(items);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private boolean databaseHasUpdated() {
        if (!latestScan.equals(listingsDb.getLatestTimeStamp())) {
            latestScan = listingsDb.getLatestTimeStamp();
            itemCache = new HashMap<>();
            professionCache = new HashMap<>();
            itemNamesCache = null;
            return true;
        }
        return false;
    }

    private void updateLatestTimeStamp() {
        latestScan = listingsDb.getLatestTimeStamp();
    }
}
