package home.crafting;

public enum Profession {
    Blacksmithing("Blacksmithing"),
    Leatherworking("Leatherworking"),
    Tailoring("Tailoring"),
    Alchemy("Alchemy"),
    Cooking("Cooking"),
    Engineering("Engineering"),
    Herbalism("Herbalism"),
    Mining("Mining"),
    Skinning("Skinning");

    private String value;

    Profession(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
