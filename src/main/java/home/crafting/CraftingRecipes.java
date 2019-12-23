package home.crafting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CraftingRecipes {
    List<String> herbs = Arrays.asList(new String[]{
            "Peacebloom",
            "Silverleaf",
            "Earthroot",
            "Mageroyal",
            "Briathorn",
            "Stranglekelp",
            "Bruiseweed",
            "Wild Steelbloom",
            "Grave Moss",
            "Kimgsblood",
            "Liferoot",
            "Fadeleaf",
            "Goldthorn",
            "Khadgar's Whisker",
            "Wintersbite",
            "Firebloom",
            "Purple Lotus",
            "Arthas' Tears",
            "Sungrass",
            "Blindweed",
            "GHost Mushroom",
            "Gromsblood",
            "Golden Sansam",
            "Dreamfoil",
            "Mountain Silversage",
            "Plaguebloom",
            "Icecap",
            "Black Lotus"});

    List<String> mining
            = Arrays.asList(new String[]{
            "Copper Ore",
            "Silver Ore",
            "Iron Ore",
            "Gold Ore",
            "Mithril Ore",
            "Truesilver Ore",
            "Dark Iron Ore",
            "Thorium Ore",
            "Small Obsidian Shard",
            "Large Obsidian Shard",
            "THorium Ore",

            "Rough Stone",
            "Heavy Stone",
            "SOlid Stone",

            "Malachite",
            "Tigerseye",
            "Shadowgem",
            "Moss Agate",
            "Lesser Moonstone",
            "Jade",
            "Citrine",
            "Aquamarine",
            "Star Ruby",
            "Black Vitrol",

            "Copper Bar",
            "Tin Bar",
            "Bronze Bar",
            "Silver Bar",
            "Iron Bar",
            "Gold Bar",
            "Steel Bar",
            "Mithril Bar",
            "Truesilver Bar",
            "Dark Iron Bar",
            "THorium Bar",
            });



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
                "Recipe: Transmute Iron to Gold",
                Stream.of(new Object[][] {{ "Iron Bar", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Gold Bar", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        all.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Transmute Mithril to Truesilver",
                Stream.of(new Object[][] {{ "Mithril Bar", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Truesilver Bar", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));

        all.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Transmute Air to Fire",
                Stream.of(new Object[][] {{ "Essence of Air", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Essence of Fire", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        all.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Transmute Earth to Life",
                Stream.of(new Object[][] {{ "Essence of Earth", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Essence of Life", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        all.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Transmute Earth to Water",
                Stream.of(new Object[][] {{ "Essence of Earth", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Essence of Water", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        all.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Transmute Life to Earth",
                Stream.of(new Object[][] {{ "Essence of Life", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Essence of Earth", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        all.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Transmute Fire to Earth",
                Stream.of(new Object[][] {{ "Essence of Fire", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Essence of Earth", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        all.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Transmute Undeath to Water",
                Stream.of(new Object[][] {{ "Essence of Undeath", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Essence of Water", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        all.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Transmute Water to Air",
                Stream.of(new Object[][] {{ "Essence of Water", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Essence of Air", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        all.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Transmute Water to Undeath",
                Stream.of(new Object[][] {{ "Essence of Water", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Essence of Undeath", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
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
