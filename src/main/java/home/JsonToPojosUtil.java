package home;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JsonToPojosUtil {
    public static List<Listing> jsonToListing(JSONObject jsonObject) {
        List<Listing> convertedListings = new LinkedList<>();


        Map<String, String> itemIdToName = getItemIdToName(jsonObject);
        JSONObject latestData = getLatestScan(jsonObject);

        String auctions = (String) latestData.get("data");
        Long timestamp = ((Long) latestData.get("ts") * 1000);
        for (String item : auctions.split(", ")) {
            String[] details = item.split("!");
            String itemName = itemIdToName.get(details[0]);

            for (Integer i=1; i<details.length; i++) {
                String[] userListings = details[i].split("!");

                for (Integer g=0; g<userListings.length; g++) {
                    String username = userListings[g].split("/")[0];

                    String[] pricingInfo = userListings[g].split("/")[1].split(",");
                    if (pricingInfo.length == 4) {
                        int numStacks = Integer.parseInt(pricingInfo[0]);
                        int stackSize = Integer.parseInt(pricingInfo[1]);
                        int bidPrice = Integer.parseInt(pricingInfo[3]);
                        int buyoutPrice = bidPrice;
                        int unitPrice = buyoutPrice/stackSize;
                        convertedListings.add(new Listing(itemName, username, numStacks, stackSize, bidPrice, buyoutPrice, unitPrice, timestamp));
                    }
                }
            }
        }

        return convertedListings;
    }

    private static JSONObject getLatestScan(JSONObject jsonObject) {
        JSONObject latest = null;
        for (Object object : (JSONArray) jsonObject.get("ah")) {
            JSONObject jsonEntry = (JSONObject) object;
            if (latest == null) {
                latest = jsonEntry;
            } else if ((Long) latest.get("ts") < (Long) jsonEntry.get("ts")) {
                latest = jsonEntry;
            }
        }
        return latest;
    }

    private static Map<String, String> getItemIdToName(JSONObject jsonObject) {
        Map<String, String> returnMap = new HashMap();
        JSONObject itemDb = (JSONObject) jsonObject.get("itemDB_2");
        for (Object itemId : itemDb.keySet()) {
            if (!((String) itemId).contains("_")) {
                String itemdesc = (String) itemDb.get(itemId);
                String itemName = itemdesc.split("\\|")[3];
                itemName = itemName.substring(2, itemName.length()-1);

                returnMap.put((String) itemId, itemName);
            }
        }

        return returnMap;
    }
}
