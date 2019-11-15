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

        String auctions = (String) ((JSONObject) ((JSONArray) jsonObject.get("ah")).get(0)).get("data");
        for (String item : auctions.split(", ")) {
            String[] details = item.split("!");
            String itemName = itemIdToName.get(details[0]);

            for (Integer i=1; i<details.length; i++) {
                String[] userListings = details[i].split("!");

                for (Integer g=0; g<userListings.length; g++) {
                    String username = userListings[g].split("/")[0];

                    String[] pricingInfo = userListings[g].split("/")[1].split(",");
                    int numStacks = Integer.parseInt(pricingInfo[0]);
                    int stackSize = Integer.parseInt(pricingInfo[1]);
                    int bidPrice = Integer.parseInt(pricingInfo[2]);
                    int buyoutPrice = bidPrice;
                    int unitPrice;
                    if (pricingInfo.length == 4) {
                        buyoutPrice = Integer.parseInt(pricingInfo[3]);
                        unitPrice = buyoutPrice/stackSize;
                    } else {
                        unitPrice = 999999999;
                    }

                    convertedListings.add(new Listing(itemName, username, numStacks, stackSize, bidPrice, buyoutPrice, unitPrice, 0l));
                }
            }
        }

        return convertedListings;
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
