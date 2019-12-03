package home;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ListingDbImpl implements ListingsDb {
    private List<Listing> ahListings = new LinkedList<>();

    public ListingDbImpl() {
        JSONObject jsonObject;
        JSONParser jsonParser = new JSONParser();

        List<Path> files = new LinkedList<>();

        try {
            files = Files.list(Paths.get("data")).map(Path::getFileName).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Path path : files) {
            try {
                FileReader reader = new FileReader("data\\" + path.toString());
                jsonObject = (JSONObject) jsonParser.parse(reader);
                reader.close();
            } catch (Exception e) {
                jsonObject = new JSONObject();
                e.printStackTrace();
            }
            addDataFromJson(jsonObject);
        }
    }

    @Override
    public void addDataFromJson(JSONObject jsonData) {
        ahListings.addAll(JsonToPojosUtil.jsonToListing((JSONObject) jsonData.get("AuctionDBSaved")));
    }

    @Override
    public List<Listing> getAllListings() {
        return ahListings;
    }

    @Override
    public ItemPageData getItemPageData(String name) {
        Long weeklyArray[][] = weeklyPriceArray(name);
        Long weeklyQuantity[][] = weeklyQuantity(name);
        Long latestTs = weeklyArray[weeklyArray.length-1][0];
        Double goldPrice = weeklyArray[weeklyArray.length-1][1].doubleValue() / 10000;


        Map<String, Integer> weeklySellers = getSellersForTime(name, latestTs);
        Map<String, Integer> monthlySellers = getSellersForTime(name, latestTs);
        return new ItemPageData(
                name,
                goldPrice,
                weeklyQuantity[weeklyQuantity.length-1][1].intValue(),
                weeklyArray,
                weeklyQuantity,
                weeklyArray,
                weeklyQuantity,
                weeklySellers,
                monthlySellers);
    }

    private Map<String, Integer> getSellersForTime(String name, Long ts) {
        Map<String, Integer> sellers = new HashMap<>();
        for (Listing listing : getListingsByName(name)) {
            if (listing.getDate() == ts) {
                Integer total = listing.getNumPerStack() * listing.getNumStacks();
                if (sellers.get(listing.getUserName()) == null) {
                    sellers.put(listing.getUserName(), total);
                } else {
                    Integer newTotal = sellers.get(listing.getUserName()) + total;
                    sellers.put(listing.getUserName(), newTotal);
                }
            }
        }

        LinkedHashMap<String, Integer> sortedSellers = new LinkedHashMap<>();
        List<Map.Entry<String, Integer>> sortedQuantities = new ArrayList<>(sellers.entrySet());
        sortedQuantities.sort(Map.Entry.comparingByValue());

        if (sortedQuantities.size() > 10) {
            sortedQuantities = sortedQuantities.subList(sortedQuantities.size()-10, sortedQuantities.size());
        }
        for (Map.Entry<String, Integer> entry : sortedQuantities) {
                sortedSellers.put(entry.getKey(), entry.getValue());
        }
        return sortedSellers;
    }

    public Long[][] weeklyPriceArray(String name) {
        Map<Long, Integer> result = new HashMap<>();
        for (Listing listing : getListingsByName(name)) {
            if (result.get(listing.getDate())==null) {
                result.put(listing.getDate(), listing.getUnitBuyout());
            } else if (listing.getUnitBuyout() < result.get(listing.getDate())) {
                result.put(listing.getDate(), listing.getUnitBuyout());
            }
        }

        Long[][] outArray = new Long[result.keySet().size()][2];
        int count = 0;
        List<Long> list = new ArrayList<>(result.keySet());
        Collections.sort(list);
        for (Long ts : list) {
            outArray[count][0] = ts;
            outArray[count][1] = Long.valueOf(result.get(ts));
            count++;
        }
        return outArray;
    }

    public Long[][] weeklyQuantity(String name) {
        Map<Long, Integer> result = new HashMap<>();
        for (Listing listing : getListingsByName(name)) {
            Integer currentTotal = result.get(listing.getDate());
            Integer itemsinListing = listing.getNumPerStack()* listing.getNumStacks();
            if (currentTotal==null) {
                result.put(listing.getDate(), itemsinListing);
            } else {
                Integer newTotal = currentTotal + (itemsinListing);
                result.put(listing.getDate(), newTotal);
            }
        }

        Long[][] outArray = new Long[result.keySet().size()][2];
        int count = 0;
        List<Long> list = new ArrayList<>(result.keySet());
        Collections.sort(list);
        for (Long ts : list) {
            outArray[count][0] = ts;
            outArray[count][1] = Long.valueOf(result.get(ts));
            count++;
        }
        return outArray;
    }

    @Override
    public List<Listing> getListingsByName(String itemName) {
        return ahListings
                .stream()
                .filter(listing -> listing.getItemName().equals(itemName))
                .sorted(unitPriceComparator)
                .collect(Collectors.toList());
    }

    @Override
    public List<Listing> getListingByUser(String userName) {
        return ahListings
                .stream()
                .filter(listing -> listing.getUserName().equals(userName))
                .collect(Collectors.toList());
    }

    Comparator<Listing> unitPriceComparator = (o1, o2) -> {
        if (o1.getUnitBuyout() > o2.getUnitBuyout()) {
            return 1;
        } else if (o1.getUnitBuyout() < o2.getUnitBuyout()){
            return -1;
        } else {
            return 0;
        }
    };
}
