package home.crafting;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CraftingRecipes {
    public static List<Recipe> getAll() {
        List<Recipe> all = new LinkedList<>();

        all.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Transmute Arcanite",
                Stream.of(new Object[][] {
                        { "Thorium Bar", 1 },
                        { "Arcane Crystal", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Arcanite Bar", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));


        all.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Flask of Supreme Power",
                Stream.of(new Object[][] {
                        { "Dreamfoil", 30 },
                        { "Mountain Silversage", 10 },
                        { "Black Lotus", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Flask of Supreme Power", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));

        all.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Flask of Distilled Wisdom",
                Stream.of(new Object[][] {
                        { "Dreamfoil", 30 },
                        { "Icecap", 10 },
                        { "Black Lotus", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Flask of Distilled Wisdom", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));

        all.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Flask of the Titans",
                Stream.of(new Object[][] {
                        { "Black Lotus", 1 },
                        { "Gromsblood", 30 },
                        { "Stonescale Oil", 30 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Flask of the Titans", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));

        all.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Greater Arcane Elixir",
                Stream.of(new Object[][] {
                        { "Dreamfoil", 3 },
                        { "Mountain Silversage", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Greater Arcane Elixir", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));

        all.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Major Mana Potion",
                Stream.of(new Object[][] {
                        { "Dreamfoil", 3 },
                        { "Icecap", 2 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Major Mana Potion", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));

        return all;
    }
}
