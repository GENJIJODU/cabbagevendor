package home;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CraftingRecipes {
    public static List<Recipe> getAll() {
        List<Recipe> all = new LinkedList<>();

        Map<String, Integer> components = new HashMap<>();
        Map<String, Integer> vendorComponents = new HashMap<>();
        Map<String, Integer> products = new HashMap<>();

        components.put("Dreamfoil", 3);
        components.put("Mountain Silversage", 1);
        products.put("Greater Arcane Elixir", 1);
        all.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Greater Arcane Elixir",
                components,
                vendorComponents,
                products
        ));

        Map<String, Integer> components2 = new HashMap<>();
        Map<String, Integer> products2 = new HashMap<>();
        components2.put("Thorium Bar", 1);
        components2.put("Arcane Crystal", 1);
        products2.put("Arcanite Bar", 1);
        all.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Transmute Arcanite",
                components2,
                vendorComponents,
                products2
        ));

        return all;
    }
}
