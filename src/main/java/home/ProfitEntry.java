package home;

public class ProfitEntry {
    private final Recipe recipe;
    private final Integer cost;
    private final Integer revenue;

    public ProfitEntry(Recipe recipe, Integer cost, Integer revenue) {
        this.recipe = recipe;
        this.cost = cost;
        this.revenue = revenue;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public Integer getCost() {
        return cost;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public Integer getProvfit() {
        return revenue-cost;
    }
}
