package home.crafting;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CraftingRecipes {
    // Mining
    public static Set<String> allHerbs() {
        Set<String> result = new HashSet<>();
        result.addAll(herbs);
        return  result;
    }
    public static Map<String, List<String>> herbMap() {
        Map<String, List<String>> result = new TreeMap<>();
        result.put("Herbs", herbs);
        return result;
    }
    public static List<String> herbs = Arrays.asList("Peacebloom",
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
            "Black Lotus"
    );

    // Mining
    public static Set<String> allMining() {
        Set<String> result = new HashSet<>();
        result.addAll(ore);
        result.addAll(stones);
        result.addAll(preciousStones);
        result.addAll(bars);
        return  result;
    }
    public static Map<String, List<String>> miningMap() {
        Map<String, List<String>> result = new TreeMap<>();
        result.put("Precious Stones", preciousStones);
        result.put("Ore", ore);
        result.put("Bars", bars);
        result.put("Stones", stones);
        return result;
    }
    public static List<String> ore
            = Arrays.asList("Copper Ore",
            "Tin Ore",
            "Silver Ore",
            "Iron Ore",
            "Gold Ore",
            "Mithril Ore",
            "Truesilver Ore",
            "Dark Iron Ore",
            "Thorium Ore",
            "Small Obsidian Shard",
            "Large Obsidian Shard",
            "Thorium Ore");
    public static List<String> stones
            = Arrays.asList("Rough Stone",
            "Coarse Stone",
            "Heavy Stone",
            "Solid Stone");
    public static List<String> preciousStones
            = Arrays.asList("Malachite",
            "Tigerseye",
            "Shadowgem",
            "Moss Agate",
            "Lesser Moonstone",
            "Jade",
            "Citrine",
            "Aquamarine",
            "Star Ruby",
            "Blood of the Mountain",
            "Black Diamond",
            "Black Vitrol",
            "Blue Sapphire",
            "Large Opal",
            "Arcane Crystal",
            "Huge Emerald");
    public static List<String> bars
            = Arrays.asList("Copper Bar",
            "Tin Bar",
            "Bronze Bar",
            "Silver Bar",
            "Iron Bar",
            "Gold Bar",
            "Steel Bar",
            "Mithril Bar",
            "Truesilver Bar",
            "Dark Iron Bar",
            "Thorium Bar");

    // Mining
    public static Set<String> allSkinning() {
        Set<String> result = new HashSet<>();
        result.addAll(endgameLeather);
        result.addAll(leather);
        result.addAll(scales);
        return  result;
    }
    public static Map<String, List<String>> skinningMap() {
        Map<String, List<String>> result = new TreeMap<>();
        result.put("Endgame Leather", endgameLeather);
        result.put("Leather", leather);
        result.put("Scales", scales);
        return result;
    }
    public static List<String> endgameLeather = Arrays.asList(
            "Devilsaur Leather",
            "Core Leather",
            "Pristine Hide of the Beast",
            "Scale of Onyxia",
            "Brilliant Chromatic Scale",
            "Black Dragonscale"
            );
    public static List<String> leather = Arrays.asList(
            "Ruined Leather Scraps",
            "Light Leather",
            "Light Hide",
            "Medium Leather",
            "Medium Hide",
            "Heavy Leather",
            "Heavy Hide",
            "Thick Leather",
            "Thick Hide",
            "Rugged Leather",
            "Rugged Hide");
    public static List<String> scales
            = Arrays.asList(
            "Black Dragonscale",
            "Blue Dragonscale",
            "Green Dragonscale",
            "Red Dragonscale",
            "Deviate Scale",
            "Slimy Murloc Scale",
            "Red Whelp Scale",
            "Thick Murloc Scale",
            "Scorpid Scale",
            "Heavy Scorpid Scale"
    );

    public static Map<String, List<Recipe>> getTailoringMapped(){
        Map<String, List<Recipe>> result = new TreeMap<>();
        result.put("Bags", getTailoringBags());
        return result;
    }
    public static List<Recipe> getTailoring() {
        List<Recipe> result = new ArrayList<>();
        result.addAll(getTailoringBags());
        return result;
    }
    public static List<Recipe> getTailoringBags() {
        List<Recipe> bags= new LinkedList<>();
        bags.add(new Recipe(
                Profession.Tailoring,
                "Recipe: Bottomless Bag",
                Stream.of(new Object[][] {
                        { "Bolt of Runecloth", 8},
                        { "Mooncloth", 12},
                        { "Large Brilliant Shard", 2},
                        { "Core Leather", 2 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Bottomless Bag", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        bags.add(new Recipe(
                Profession.Tailoring,
                "Recipe: Big Bag of Enchantment",
                Stream.of(new Object[][] {
                        { "Bolt of Runecloth", 6},
                        { "Enchanted Leather", 4},
                        { "Large Brilliant Shard", 4},
                        { "Ironweb Spider Silk", 4 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Big Bag of Enchantment", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        bags.add(new Recipe(
                Profession.Tailoring,
                "Recipe: Enchanted Runecloth Bag",
                Stream.of(new Object[][] {
                        { "Bolt of Runecloth", 5},
                        { "Greater Eternal Essence", 2},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Enchanted Runecloth Bag", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        bags.add(new Recipe(
                Profession.Tailoring,
                "Recipe: Mooncloth Bag",
                Stream.of(new Object[][] {
                        { "Bolt of Runecloth", 4},
                        { "Mooncloth", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Mooncloth Bag", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        bags.add(new Recipe(
                Profession.Tailoring,
                "Recipe: Core Felcloth Bag",
                Stream.of(new Object[][] {
                        { "Felcloth", 20},
                        { "Core Leather", 16},
                        { "Bloodvine", 8},
                        { "Essence of Fire", 4},
                        { "Ironweb Spider Silk", 4 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Core Felcloth Bag", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        bags.add(new Recipe(
                Profession.Tailoring,
                "Recipe: Felcloth Bag",
                Stream.of(new Object[][] {
                        { "Felcloth", 12},
                        { "Enchanted Leather", 6},
                        { "Dark Rune", 2},
                        { "Ironweb Spider Silk", 4 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Felcloth Bag", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        bags.add(new Recipe(
                Profession.Tailoring,
                "Recipe: Cenarion Herb Bag",
                Stream.of(new Object[][] {
                        { "Bolt of Runecloth", 5},
                        { "Purple Lotus", 10},
                        { "Morrowgrain", 8},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Cenarion Herb Bag", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        bags.add(new Recipe(
                Profession.Tailoring,
                "Recipe: Runecloth Bag",
                Stream.of(new Object[][] {
                        { "Bolt of Runecloth", 5},
                        { "Rugged Leather", 2},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Runecloth Bag", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        bags.add(new Recipe(
                Profession.Tailoring,
                "Recipe: Mageweave Bag",
                Stream.of(new Object[][] {
                        { "Bolt of Mageweave", 4},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Mageweave Bag", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        bags.add(new Recipe(
                Profession.Tailoring,
                "Recipe: Small Silk Pack",
                Stream.of(new Object[][] {
                        { "Bolt of Silk Cloth", 3},
                        {"Heavy Leather", 2},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Small Silk Pack", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        bags.add(new Recipe(
                Profession.Tailoring,
                "Recipe: Woolen Bag",
                Stream.of(new Object[][] {
                        { "Bolt of Woolen Cloth", 3},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Woolen Bag", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        bags.add(new Recipe(
                Profession.Tailoring,
                "Recipe: Linen Bag",
                Stream.of(new Object[][] {
                        { "Bolt of Linen Cloth", 3},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Linen Bag", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        return bags;
    }
    public static List<Recipe> getTailoringGear() {
        List<Recipe> bags= new LinkedList<>();
        bags.add(new Recipe(
                Profession.Tailoring,
                "Recipe: Glacial CLoak",
                Stream.of(new Object[][] {
                        { "Frozen Rune", 5},
                        { "Bolt of Runecloth", 4},
                        { "Essence of Water", 2},
                        { "Ironweb Spider Silk", 4 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Glacial Cloak", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        bags.add(new Recipe(
                Profession.Tailoring,
                "Recipe: Gaea's Embrace",
                Stream.of(new Object[][] {
                        { "Bloodvine", 1},
                        { "Mooncloth", 2},
                        { "Living Essence", 4},
                        { "Ironweb Spider Silk", 4 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Gaea's Embrace", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        bags.add(new Recipe(
                Profession.Tailoring,
                "Recipe: Cloak of Warding",
                Stream.of(new Object[][] {
                        { "Bolt of Runecloth", 12},
                        { "Guardian Stone", 4},
                        { "Arcanite Bar", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Cloak of Warding", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        bags.add(new Recipe(
                Profession.Tailoring,
                "Recipe: Sylvan Vest",
                Stream.of(new Object[][] {
                        { "Bolt of Runecloth", 4},
                        { "Bloodvine", 2},
                        { "Living Essence", 2},
                        { "Ironweb Spider Silk", 2},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Sylvan Vest", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        bags.add(new Recipe(
                Profession.Tailoring,
                "Recipe: Glacial Vest",
                Stream.of(new Object[][] {
                        { "Frozen Rune", 7},
                        { "Bolt of Runecloth", 8},
                        { "Essence of Water", 6},
                        { "Ironweb Spider Silk", 8 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Glacial Vest", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        bags.add(new Recipe(
                Profession.Tailoring,
                "Recipe: Flarecore Robe",
                Stream.of(new Object[][] {
                        { "Mooncloth", 10},
                        { "Fiery Core", 2},
                        { "Lava Core", 3},
                        { "Essence of FIre", 6},
                        { "Ironweb Spider Silk", 4 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Flarecore Robe", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        bags.add(new Recipe(
                Profession.Tailoring,
                "Recipe: Bloodvine Vest",
                Stream.of(new Object[][] {
                        { "Mooncloth", 3},
                        { "Bolt of Runecloth", 4},
                        { "Powerful Mojo", 4},
                        { "Bloodvine", 5},
                        { "Ironweb Spider Silk", 2},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Bloodvine Vest", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        bags.add(new Recipe(
                Profession.Tailoring,
                "Recipe: Bloodvine Boots",
                Stream.of(new Object[][] {
                        { "Mooncloth", 3},
                        { "Bolt of Runecloth", 4},
                        { "Enchanted Leather", 4},
                        { "Bloodvine", 3},
                        { "Ironweb Spider Silk", 4},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Bloodvine Boots", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        bags.add(new Recipe(
                Profession.Tailoring,
                "Recipe: Runed Stygian Boots",
                Stream.of(new Object[][] {
                        { "Dark Rune", 6},
                        { "Bolt of Runecloth", 4},
                        { "Felcloth", 4},
                        { "Enchanted Leather", 2},
                        { "Ironweb Spider Silk", 2},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Runed Stygian Boots", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        bags.add(new Recipe(
                Profession.Tailoring,
                "Recipe: Glacial Gloves",
                Stream.of(new Object[][] {
                        { "Frozen Rune", 5},
                        { "Bolt of Runecloth", 4},
                        { "Essence of Water", 4},
                        { "Ironweb Spider Silk", 2},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Glacial Gloves", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        bags.add(new Recipe(
                Profession.Tailoring,
                "Recipe: Inferno Gloves",
                Stream.of(new Object[][] {
                        { "Star Ruby", 2},
                        { "Bolt of Runecloth", 12},
                        { "Essence of Fire", 10},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Inferno Gloves", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        bags.add(new Recipe(
                Profession.Tailoring,
                "Recipe: Flarecore Gloves",
                Stream.of(new Object[][] {
                        { "Fiery Core", 6},
                        { "Essence of Fire", 4},
                        { "Bolt of Runecloth", 8},
                        { "Enchanted Leather", 2},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Flarecore Gloves", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        bags.add(new Recipe(
                Profession.Tailoring,
                "Recipe: Gloves of Spell Mastery",
                Stream.of(new Object[][] {
                        { "Bolt of Runecloth", 10},
                        { "Mooncloth", 10},
                        { "Ghost Dye", 10},
                        { "Golden Pearl", 6},
                        { "Huge Emerald", 6},
                        { "Enchanted Leather", 8},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Gloves of Spell Mastery", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        bags.add(new Recipe(
                Profession.Tailoring,
                "Recipe: Felcloth Gloves",
                Stream.of(new Object[][] {
                        { "Bolt of Runecloth", 12},
                        { "Felcloth", 20},
                        { "Demonic Rune", 6},
                        { "Essence of Undeath", 8},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Felcloth Gloves", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        bags.add(new Recipe(
                Profession.Tailoring,
                "Recipe: Runecloth Gloves",
                Stream.of(new Object[][] {
                        { "Bolt of Runecloth", 4},
                        { "Rugged Leather", 4},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Runecloth Gloves", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        return bags;
    }

    public static Map<String, List<Recipe>> getAlchemyMap(){
        Map<String, List<Recipe>> result = new TreeMap<>();
        result.put("Transmutations", getTransmutes());
        result.put("Flasks", getFlasks());
        result.put("Regeneration Potions", getHpManaPots());
        result.put("Greater Protection Potions", getProtPots());
        result.put("Stat-Increasing Potions", getRaidPots());
        result.put("Utility Potions", getUtilityPots());
        return result;
    }
    public static List<Recipe> getAllAlchemy() {
        List<Recipe> result = new ArrayList<>();
        result.addAll(getTransmutes());
        result.addAll(getFlasks());
        result.addAll(getHpManaPots());
        result.addAll(getProtPots());
        result.addAll(getRaidPots());
        result.addAll(getUtilityPots());
        return result;
    }
    public static List<Recipe> getTransmutes() {
        List<Recipe> transmutes = new LinkedList<>();
        transmutes.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Transmute Arcanite",
                Stream.of(new Object[][]{
                        {"Thorium Bar", 1},
                        {"Arcane Crystal", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Arcanite Bar", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        transmutes.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Transmute Iron to Gold",
                Stream.of(new Object[][]{{"Iron Bar", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Gold Bar", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        transmutes.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Transmute Mithril to Truesilver",
                Stream.of(new Object[][]{{"Mithril Bar", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Truesilver Bar", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        transmutes.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Transmute Air to Fire",
                Stream.of(new Object[][]{{"Essence of Air", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Essence of Fire", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        transmutes.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Transmute Earth to Life",
                Stream.of(new Object[][]{{"Essence of Earth", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Essence of Life", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        transmutes.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Transmute Earth to Water",
                Stream.of(new Object[][]{{"Essence of Earth", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Essence of Water", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        transmutes.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Transmute Life to Earth",
                Stream.of(new Object[][]{{"Essence of Life", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Essence of Earth", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        transmutes.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Transmute Fire to Earth",
                Stream.of(new Object[][]{{"Essence of Fire", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Essence of Earth", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        transmutes.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Transmute Undeath to Water",
                Stream.of(new Object[][]{{"Essence of Undeath", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Essence of Water", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        transmutes.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Transmute Water to Air",
                Stream.of(new Object[][]{{"Essence of Water", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Essence of Air", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        transmutes.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Transmute Water to Undeath",
                Stream.of(new Object[][]{{"Essence of Water", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Essence of Undeath", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        return transmutes;
    }
    public static List<Recipe> getFlasks() {
        List<Recipe> flasks = new LinkedList<>();
        flasks.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Flask of Supreme Power",
                Stream.of(new Object[][]{
                        {"Black Lotus", 1},
                        {"Mountain Silversage", 10},
                        {"Dreamfoil", 30},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Flask of Supreme Power", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        flasks.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Flask of Distilled Wisdom",
                Stream.of(new Object[][]{
                        {"Black Lotus", 1},
                        {"Icecap", 10},
                        {"Dreamfoil", 30},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Flask of Distilled Wisdom", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        flasks.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Flask of the Titans",
                Stream.of(new Object[][]{
                        {"Black Lotus", 1},
                        {"Stonescale Oil", 10},
                        {"Gromsblood", 30},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Flask of the Titans", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        flasks.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Flask of Petrification",
                Stream.of(new Object[][]{
                        {"Black Lotus", 1},
                        {"Mountain Silversage", 30},
                        {"Stonescale Oil", 30},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Flask of Petrification", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        flasks.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Flask of Chromatic Resistance",
                Stream.of(new Object[][]{
                        {"Black Lotus", 1},
                        {"Mountain Silversage", 30},
                        {"Icecap", 30},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Flask of Chromatic Resistance", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        return flasks;
    }
    public static List<Recipe> getHpManaPots() {
        List<Recipe> hpManaPots = new LinkedList<>();
        hpManaPots.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Major Mana Potion",
                Stream.of(new Object[][]{
                        {"Dreamfoil", 3},
                        {"Icecap", 2},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Major Mana Potion", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        hpManaPots.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Major Healing Potion",
                Stream.of(new Object[][]{
                        {"Golden Sansam", 2},
                        {"Mountain Silversage", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Major Healing Potion", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        return hpManaPots;
    }
    public static List<Recipe> getProtPots() {
        List<Recipe> protPots = new LinkedList<>();
        protPots.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Greater Arcane Protection Potion",
                Stream.of(new Object[][]{
                        {"Dream Dust", 1},
                        {"Dreamfoil", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Greater Arcane Protection Potion", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        protPots.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Greater Fire Protection Potion",
                Stream.of(new Object[][]{
                        {"Elemental Fire", 1},
                        {"Dreamfoil", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Greater Fire Protection Potion", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        protPots.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Greater Frost Protection Potion",
                Stream.of(new Object[][]{
                        {"Elemental Water", 1},
                        {"Dreamfoil", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Greater Frost Protection Potion", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        protPots.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Greater Holy Protection Potion",
                Stream.of(new Object[][]{
                        {"Elemental Air", 1},
                        {"Dreamfoil", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Greater Holy Protection Potion", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        protPots.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Greater Nature Protection Potion",
                Stream.of(new Object[][]{
                        {"Elemental Earth", 1},
                        {"Dreamfoil", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Greater Nature Protection Potion", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        protPots.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Greater Shadow Protection Potion",
                Stream.of(new Object[][]{
                        {"Shadow Oil", 1},
                        {"Dreamfoil", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Greater Shadow Protection Potion", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        return protPots;
    }
    public static List<Recipe> getRaidPots() {
        List<Recipe> raidPots = new LinkedList<>();
        raidPots.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Greater Arcane Elixir",
                Stream.of(new Object[][]{
                        {"Dreamfoil", 3},
                        {"Mountain Silversage", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Greater Arcane Elixir", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        raidPots.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Elixir of Brute Force",
                Stream.of(new Object[][]{
                        {"Gromsblood", 2},
                        {"Plaguebloom", 2},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Elixir of Brute Force", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        raidPots.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Elixir of Shadow Power",
                Stream.of(new Object[][]{
                        {"Ghost Mushroom", 3},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Elixir of Shadow Power", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        raidPots.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Elixir of The Mongoose",
                Stream.of(new Object[][]{
                        {"Mountain Silversage", 2},
                        {"Plaguebloom", 2},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Elixir of The Mongoose", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        return raidPots;
    }
    public static List<Recipe> getUtilityPots() {
        List<Recipe> utilityPots= new LinkedList<>();
        utilityPots.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Free Action Potion",
                Stream.of(new Object[][] {
                        { "Blackmouth Oil", 2 },
                        { "Stranglekelp", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Free Action Potion", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        utilityPots.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Swiftness Potion",
                Stream.of(new Object[][] {
                        { "Swiftthistle", 1 },
                        { "Briarthorn", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Swiftness Potion", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        utilityPots.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Invisibility Potion",
                Stream.of(new Object[][] {
                        { "Ghost Mushroom", 1 },
                        { "Sungrass", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Invisibility Potion", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        utilityPots.add(new Recipe(
                Profession.Alchemy,
                "Recipe: Elixir of Detect Lesser Invisibility",
                Stream.of(new Object[][] {
                        { "Khadgar's Whisker", 1 },
                        { "Fadeleaf", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][] {{ "Elixir of Detect Lesser Invisibility", 1 },}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        return utilityPots;
    }


    public static Map<String, List<Recipe>> getCookingMapped(){
        Map<String, List<Recipe>> result = new TreeMap<>();
        result.put("Mana Food", getManaFood());
        result.put("Melee Food", getMeleeFood());
        result.put("Stamina Food", getStaminaFood());
        return result;
    }
    public static List<Recipe> getAllCooking() {
        List<Recipe> result = new ArrayList<>();
        result.addAll(getManaFood());
        result.addAll(getMeleeFood());
        result.addAll(getStaminaFood());
        return result;
    }
    public static List<Recipe> getManaFood() {
        List<Recipe> manaFood = new LinkedList<>();
        manaFood.add(new Recipe(
                Profession.Cooking,
                "Recipe: Nightfin Soup",
                Stream.of(new Object[][]{
                        {"Raw Nightfin Snapper", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Nightfin Soup", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        manaFood.add(new Recipe(
                Profession.Cooking,
                "Recipe: Runn Tum Tuber Suprise",
                Stream.of(new Object[][]{
                        {"Runn Tum Tuber", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Runn Tum Tuber Surprise", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        return manaFood;
    }
    public static List<Recipe> getStaminaFood() {
        List<Recipe> staminaFood = new LinkedList<>();
        staminaFood.add(new Recipe(
                Profession.Cooking,
                "Recipe: Tender Wolf Steak",
                Stream.of(new Object[][]{
                        {"Tender Wolf Meat", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Tender Wolf Steak", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        staminaFood.add(new Recipe(
                Profession.Cooking,
                "Recipe: Monster Omelet",
                Stream.of(new Object[][]{
                        {"Giant Egg", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Monster Omelet", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        staminaFood.add(new Recipe(
                Profession.Cooking,
                "Recipe: Spiced Chili Crab",
                Stream.of(new Object[][]{
                        {"Tender Crab Meat", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Spiced Chili Crab", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        return staminaFood;
    }
    public static List<Recipe> getMeleeFood() {
        List<Recipe> meleeFood = new LinkedList<>();
        meleeFood.add(new Recipe(
                Profession.Cooking,
                "Recipe: Grilled Squid",
                Stream.of(new Object[][]{
                        {"Winter Squid", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Grilled Squid", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        meleeFood.add(new Recipe(
                Profession.Cooking,
                "Recipe: Thistle Tea",
                Stream.of(new Object[][]{
                        {"Swiftthistle", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Thistle Tea", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        meleeFood.add(new Recipe(
                Profession.Cooking,
                "Recipe: Dragonbreath Chili",
                Stream.of(new Object[][]{
                        {"Mystery Meat", 1},
                        {"Small Flame Sac", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Dragonbreath Chili", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        return meleeFood;
    }

    public static Map<String, List<Recipe>> getEngineeringMapped(){
        Map<String, List<Recipe>> result = new TreeMap<>();
        result.put("Explosives", getEngExplosives());
        result.put("Crafting Materials", getEngCraftMaterials());
        return result;
    }
    public static List<Recipe> getAllEngineering() {
        List<Recipe> result = new ArrayList<>();
        result.addAll(getEngExplosives());
        result.addAll(getEngCraftMaterials());
        return result;
    }
    public static List<Recipe> getEngExplosives() {
        List<Recipe> explosives = new LinkedList<>();
        explosives.add(new Recipe(
                Profession.Engineering,
                "Recipe: Goblin Sapper Charge",
                Stream.of(new Object[][]{
                        {"Mageweave Cloth", 1},
                        {"Solid Blasting Powder", 3},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Goblin Sapper Charge", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        explosives.add(new Recipe(
                Profession.Engineering,
                "Recipe: Dense Dynamite",
                Stream.of(new Object[][]{
                        {"Runecloth", 3},
                        {"Dense Blasting Powder", 3},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Dense Dynamite", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        explosives.add(new Recipe(
                Profession.Engineering,
                "Recipe: Thorium Grenade",
                Stream.of(new Object[][]{
                        {"Thorium Widget", 1},
                        {"Thorium Bar", 3},
                        {"Runecloth", 3},
                        {"Dense Blasting Powder", 3},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Thorium Grenade", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        explosives.add(new Recipe(
                Profession.Engineering,
                "Recipe: Arcane Bomb",
                Stream.of(new Object[][]{
                        {"Delicate Aracnite Converter", 1},
                        {"Thorium Bar", 3},
                        {"Runecloth", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Arcane Bomb", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        explosives.add(new Recipe(
                Profession.Engineering,
                "Recipe: Dark Iron Bomb",
                Stream.of(new Object[][]{
                        {"Thorium Widget", 1},
                        {"Dense Blasting Powder", 3},
                        {"Runecloth", 3},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{ "Dark Iron Bomb", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        return explosives;
    }
    public static List<Recipe> getEngCraftMaterials() {
        List<Recipe> craftMaterials = new LinkedList<>();
        craftMaterials.add(new Recipe(
                Profession.Engineering,
                "Recipe: Delicate Arcanite Converter",
                Stream.of(new Object[][]{
                        {"Arcanite Bar", 1},
                        {"Ironweb Spider Silk", 1}}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Delicate Arcanite Converter", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        craftMaterials.add(new Recipe(
                Profession.Engineering,
                "Recipe: Fused Wiring",
                Stream.of(new Object[][]{
                        {"Delicate Copper Wire", 3},
                        {"Essence of Fire", 2},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Fused Wiring", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        craftMaterials.add(new Recipe(
                Profession.Engineering,
                "Recipe: Thorium Tube",
                Stream.of(new Object[][]{
                        {"Thorium Bar", 6},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Thorium Tube", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        craftMaterials.add(new Recipe(
                Profession.Engineering,
                "Recipe: Truesilver Transformer",
                Stream.of(new Object[][]{
                        {"Truesilver Bar", 3},
                        {"Elemental Air", 1},
                        {"Elemental Earth", 2},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Truesilver Transformer", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        craftMaterials.add(new Recipe(
                Profession.Engineering,
                "Recipe: Thorium Widget",
                Stream.of(new Object[][]{
                        {"Thorium Bar", 3},
                        {"Runecloth", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Thorium Widget", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        craftMaterials.add(new Recipe(
                Profession.Engineering,
                "Recipe: Dense Blasting Powder",
                Stream.of(new Object[][]{
                        {"Dense Stone", 2},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])),
                new HashMap<>(),
                Stream.of(new Object[][]{{"Dense Blasting Powder", 1},}).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]))
        ));
        return craftMaterials;
    }
}
