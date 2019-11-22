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

    @GetMapping("/crafting/{profession}")
    @ResponseBody
    public List<ProfitEntry> getMostProfitable(@PathVariable String profession) {
        return getMostProfitable();
    }

    public List<ProfitEntry> getMostProfitable() {
        List<ProfitEntry> result = new LinkedList<>();
        for (Recipe recipe : craftingDb.getRecipeByProfession("Alchemy")) {
            result.add(getProfit(recipe));
        }
        result.sort((o1, o2) -> {
            if (o1.getProvfit() > o2.getProvfit()) {
                return 1;
            } else if (o1.getProvfit() < o2.getProvfit()) {
                return -1;
            } else {
                return 0;
            }
        });

        return result;
    }

    public ProfitEntry getProfit(Recipe recipe) {
        Integer cost = 0;
        for (Map.Entry<String, Integer> ingredient : recipe.getAhIngredients().entrySet()) {
            cost += listingsDb.getPriceForItem(ingredient.getKey()) * ingredient.getValue();
        }

        Integer revenue = listingsDb.getPriceForItem(recipe.getProduct());
        return new ProfitEntry(recipe, cost, revenue);
    }
}
