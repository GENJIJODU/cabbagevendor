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

    @RequestMapping("/profession/{profession}")
    public String generateProfessionPage(@PathVariable String profession, Model model) {
        model.addAttribute("data", listingsDb.getItemPageData("Dreamfoil"));
        return "pageData";
    }

    @RequestMapping("/home")
    public String getHomePage() {
        return "homePage";
    }

    @PostMapping(value="/itemPage")
    public String getItemPage(@RequestParam("itemName") String itemName, Model model) {
        return generateItemPage(itemName, model);
    }
}
