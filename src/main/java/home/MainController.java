package home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController { 

    ListingsDb listingsDb;

    @Autowired
    public MainController(ListingsDb listingsDb) {
        this.listingsDb = listingsDb;
    }

    @RequestMapping("/itemPage/{itemName}")
    public String generateItemPage(@PathVariable String itemName, Model model) {
        model.addAttribute("data", listingsDb.getItemPageData(itemName));
        return "pageData";
    }

    @RequestMapping("/home")
    public String getHomePage() {
        return "homePage";
    }

    @PostMapping(value="/itemPage")
    public String getItemPage(@RequestBody String itemName, Model model) {
        System.out.println("POST RECEIVED:" + itemName);
        return generateItemPage(itemName.split("=")[1], model);
    }
}
