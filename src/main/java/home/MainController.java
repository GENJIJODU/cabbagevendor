package home; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class MainController { 

    ListingsDb listingsDb;
    CraftingDb craftingDb;

    @Autowired
    public MainController(ListingsDb listingsDb,
                          CraftingDb craftingDb) {
        this.listingsDb = listingsDb;
        this.craftingDb = craftingDb;
    }

    @GetMapping("/items/{itemName}")
    @ResponseBody
    public List<Listing> getItem(@PathVariable String itemName) {
        return listingsDb.getListingsByName(itemName);
    }

    @GetMapping("pageData/{itemName}")
    @ResponseBody
    public ItemPageData getWeeklyPrices(@PathVariable String itemName) {
        return listingsDb.getItemPageData(itemName);
    }


}
