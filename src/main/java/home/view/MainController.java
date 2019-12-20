package home.view;

import home.crafting.Profession;
import home.database.Dao;
import home.database.ListingsDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    Dao dao;

    @Autowired
    public MainController(Dao dao) {
        this.dao = dao;
    }

    @RequestMapping("/itemPage/{itemName}")
    public String generateItemPage(@PathVariable String itemName, Model model) {
        model.addAttribute("data", dao.getItemPageData(itemName));
        model.addAttribute("itemNames", dao.getItemNames());
        return "pageData";
    }

    @RequestMapping("/profession/{profession}")
    public String generateProfessionPage(@PathVariable String profession, Model model) {
        model.addAttribute("profession", profession);
        model.addAttribute("itemNames", dao.getItemNames());
        model.addAttribute("profitEntries", dao.getProfitEntries(Profession.valueOf(profession)));
        return "professionPage";
    }

    @RequestMapping("/home")
    public String getHomePage(Model model) {
        model.addAttribute("itemNames", dao.getItemNames());
        return "homePage";
    }

    @PostMapping(value="/itemPage")
    public String getItemPage(@RequestParam("itemName") String itemName, Model model) {
        return generateItemPage(itemName, model);
    }
}
