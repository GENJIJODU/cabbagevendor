package home;

import java.util.HashMap;

public class Recipe {
    private final String profession;
    private final String product;
    private final HashMap<String, Integer> ahIngredients;
    private final HashMap<String, Integer> vendorIngredients;
    private final Integer vendorIngredientCost;

    public Recipe(String profession, String product, HashMap<String, Integer> ahIngredients, HashMap<String, Integer> vendorIngredients, Integer vendorIngredientCost) {
        this.profession = profession;
        this.product = product;
        this.ahIngredients = ahIngredients;
        this.vendorIngredients = vendorIngredients;
        this.vendorIngredientCost = vendorIngredientCost;
    }

    public String getProfession() {
        return profession;
    }

    public String getProduct() {
        return product;
    }

    public HashMap<String, Integer> getAhIngredients() {
        return ahIngredients;
    }

    public HashMap<String, Integer> getVendorIngredients() {
        return vendorIngredients;
    }

    public Integer getVendorIngredientCost() {
        return vendorIngredientCost;
    }
}

