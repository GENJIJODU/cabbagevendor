package home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController { 

    ListingsDb listingsDb;

    @Autowired
    public MainController(ListingsDb listingsDb) {
        this.listingsDb = listingsDb;
    }


    @RequestMapping("/home")
    public String getHomePage() {
        return "home";
    }

    @RequestMapping("/itemPage/{itemName}")
    public String generateItemPage(@PathVariable String itemName, Model model) {
        model.addAttribute("data", listingsDb.getItemPageData(itemName));
        return "pageData";
    }

    @PostMapping("/itemPage")
    public String generateItempage(@RequestBody String itemName, Model model) {
        String name = itemName.split("=")[1];
        model.addAttribute("data", listingsDb.getItemPageData(name));
        return "pageData";
    }
}
