package home;

import java.util.Map;

public class Recipe {
    private final Profession profession;
    private final String name;
    private final Map<String, Integer> components;
    private final Map<String, Integer> vendorComponents;

    public Recipe(Profession profession, String name, Map<String, Integer> components, Map<String, Integer> vendorComponents) {
        this.profession = profession;
        this.name = name;
        this.components = components;
        this.vendorComponents = vendorComponents;
    }

    public Profession getProfession() {
        return profession;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getComponents() {
        return components;
    }

    public Map<String, Integer> getVendorComponents() {
        return vendorComponents;
    }
}
