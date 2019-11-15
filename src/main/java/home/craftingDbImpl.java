package home;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class craftingDbImpl implements CraftingDb {

    private List<Recipe> recipes = new LinkedList<>();

    public craftingDbImpl() {
        // add recipes. big chunk o' data entry here.
    }

    @Override
    public List<Recipe> getRecipeByProfession(String profession) {
        return recipes.stream()
                .filter(recipe -> recipe.getProfession().equals(profession))
                .collect(Collectors.toList());
    }
}
