package home.crafting;

import java.util.Map;

public class Recipe {
    private Profession profession;
    private String name;
    private Map<String, Integer> components;
    private Map<String, Integer> vendorComponents;
    private Map<String, Integer> products;

    public Recipe(Profession profession, String name, Map<String, Integer> components, Map<String, Integer> vendorComponents, Map<String, Integer> products) {
        this.profession = profession;
        this.name = name;
        this.components = components;
        this.vendorComponents = vendorComponents;
        this.products = products;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getComponents() {
        return components;
    }

    public void setComponents(Map<String, Integer> components) {
        this.components = components;
    }

    public Map<String, Integer> getVendorComponents() {
        return vendorComponents;
    }

    public void setVendorComponents(Map<String, Integer> vendorComponents) {
        this.vendorComponents = vendorComponents;
    }

    public Map<String, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Integer> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "profession=" + profession +
                ", name='" + name + '\'' +
                ", components=" + components +
                ", vendorComponents=" + vendorComponents +
                ", products=" + products +
                '}';
    }
}
