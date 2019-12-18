package home.view;

import home.database.Listing;

import java.util.*;

public class HCUtil {
    public static Long[][] listToPriceArray(List<Listing> listings) {
        Map<Long, Integer> result = new HashMap<>();
        for (Listing listing : listings) {
            if (result.get(listing.getDate())==null) {
                result.put(listing.getDate(), listing.getUnitBuyout());
            } else if (listing.getUnitBuyout() < result.get(listing.getDate())) {
                result.put(listing.getDate(), listing.getUnitBuyout());
            }
        }

        return mapToArray(result);
    }

    public static Long[][] listToQuantityArray(List<Listing> listings) {
        Map<Long, Integer> result = new HashMap<>();
        for (Listing listing : listings) {
            Integer currentTotal = result.get(listing.getDate());
            Integer itemsInListing = listing.getNumPerStack()* listing.getNumStacks();
            if (currentTotal==null) {
                result.put(listing.getDate(), itemsInListing);
            } else {
                Integer newTotal = currentTotal + (itemsInListing);
                result.put(listing.getDate(), newTotal);
            }
        }

        return mapToArray(result);
    }


    private static Long[][] mapToArray(Map<Long, Integer> result) {
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

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
