package home.crafting;

import java.util.Map;

public class ProfitEntry {
    private String name;
    private Map<String, Integer> components;
    public Map<String, Integer> products;
    private Double craftingPrice;
    private Double salePrice;
    private Double ahCut;
    private Double totalprofit;

    public ProfitEntry() {
    }

    public ProfitEntry(String name, Map<String, Integer> components, Double craftingPrice, Double salePrice, Double ahCut, Double totalprofit) {
        this.name = name;
        this.components = components;
        this.craftingPrice = craftingPrice;
        this.salePrice = salePrice;
        this.ahCut = ahCut;
        this.totalprofit = totalprofit;
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

    public Double getCraftingPrice() {
        return craftingPrice;
    }

    public void setCraftingPrice(Double craftingPrice) {
        this.craftingPrice = craftingPrice;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Double getAhCut() {
        return ahCut;
    }

    public void setAhCut(Double ahCut) {
        this.ahCut = ahCut;
    }

    public Double getTotalprofit() {
        return totalprofit;
    }

    public void setTotalprofit(Double totalprofit) {
        this.totalprofit = totalprofit;
    }

    @Override
    public String toString() {
        return "ProfitEntry{" +
                "name='" + name + '\'' +
                ", components=" + components +
                ", craftingPrice=" + craftingPrice +
                ", salePrice=" + salePrice +
                ", ahCut=" + ahCut +
                ", totalprofit=" + totalprofit +
                '}';
    }
}
