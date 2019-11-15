package home;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListingDbImpl implements ListingsDb {
    private List<Listing> ahListings;

    public ListingDbImpl() {
        JSONObject jsonObject;
        JSONParser jsonParser = new JSONParser();

        try {
            FileReader reader = new FileReader("C:\\data\\AuctionDb.json");
            jsonObject = (JSONObject) jsonParser.parse(reader);
            reader.close();
        } catch (Exception e) {
            jsonObject = new JSONObject();
            e.printStackTrace();
        }
        addDataFromJson(jsonObject);
    }

    @Override
    public void addDataFromJson(JSONObject jsonData) {
        ahListings = JsonToPojosUtil.jsonToListing(jsonData);
    }

    @Override
    public List<Listing> getAllListings() {
        return ahListings;
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

    @Override
    public Integer getPriceForItem(String itemName) {
        return ahListings
                .stream()
                .filter(listing -> listing.getItemName().equals(itemName))
                .sorted(unitPriceComparator)
                .findFirst()
                .get()
                .getUnitBuyout();
    }

    Comparator<Listing> unitPriceComparator = new Comparator<Listing>() {
        @Override
        public int compare(Listing o1, Listing o2) {
            if (o1.getUnitBuyout() > o2.getUnitBuyout()) {
                return 1;
            } else if (o1.getUnitBuyout() < o2.getUnitBuyout()){
                return -1;
            } else {
                return 0;
            }
        }
    };
}
