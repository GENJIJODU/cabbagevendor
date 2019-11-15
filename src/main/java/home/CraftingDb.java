package home;

import java.util.List;

public interface CraftingDb {
    List<Recipe> getRecipeByProfession(String profession);
}
