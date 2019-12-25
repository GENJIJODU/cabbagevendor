package home.crafting;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CraftingRecipes {
    public static Set<String> herbs = new HashSet<>(Arrays.asList("Peacebloom",
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
    ));

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

    public static Map<String, List<Recipe>> getAllMapped(){
        Map<String, List<Recipe>> result = new TreeMap<>();
        result.put("Transmutations", getTransmutes());
        result.put("Flasks", getFlasks());
        result.put("Regeneration Potions", getHpManaPots());
        result.put("Greater Protection Potions", getProtPots());
        result.put("Stat-Increasing Potions", getRaidPots());
        result.put("Utility Potions", getUtilityPots());
        return result;
    }

    public static List<Recipe> getAll() {
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
}
