package home;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class JsonToPojosUtil {

    public static Map<Long, List<Listing>> loadFromAHDB() {
        Map<Long, List<Listing>> result = new HashMap<>();

        List<Path> files = getFiles();

        JSONObject jsonObject;
        for (Path path : files) {
            jsonObject = getJsonFromPath(path);
            Map<String, String> itemIdToName = getItemIdToName(jsonObject);
            for (JSONObject scan : getScans(jsonObject)) {
                long ts = getTimeStamp(scan);
                if (result.get(ts) == null) {
                    result.put(ts, scanToListings(scan, itemIdToName));
                }
            }
        }
        return result;
    }

    private static JSONObject getJsonFromPath(Path path) {
        JSONParser jsonParser = new JSONParser();
        JSONObject result = new JSONObject();
        try {
            FileReader reader = new FileReader("data/" + path.toString());
            result = (JSONObject) ((JSONObject) jsonParser.parse(reader)).get("AuctionDBSaved");
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    private static List<Path> getFiles() {
        List<Path> files = new LinkedList<>();
        try {
            files = Files.list(Paths.get("data")).map(Path::getFileName).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();

        }
        return files;
    }


    private static List<Listing> scanToListings(JSONObject scan, Map<String, String> itemIdToName) {
        List<Listing> convertedListings = new LinkedList<>();
        String auctions = (String) scan.get("data");
        Long timestamp = ((Long) scan.get("ts") * 1000);
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
                        convertedListings.add(new Listing(itemName, username, 1, stackSize, bidPrice, buyoutPrice, unitPrice, timestamp));
                    }
                }
            }
        }

        return convertedListings;
    }

    private static List<JSONObject> getScans(JSONObject ahDbData) {
        return (JSONArray) ahDbData.get("ah");
    }

    private static long getTimeStamp(JSONObject scan) {
        return (long) scan.get("ts");
    }

    private static List<JSONObject> getLatestScan(JSONObject jsonObject) {
        JSONObject latest = null;
        for (Object object : (JSONArray) jsonObject.get("ah")) {
            JSONObject jsonEntry = (JSONObject) object;
            if (latest == null) {
                latest = jsonEntry;
            } else if ((Long) latest.get("ts") < (Long) jsonEntry.get("ts")) {
                latest = jsonEntry;
            }
        }
        List<JSONObject> result = new LinkedList<>();
        result.add(latest);
        return result;
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
