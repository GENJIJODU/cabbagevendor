package home;

import java.util.Map;

public class ItemPageData {

    private String itemName;
    private Double price;
    private Integer quantity;
    private Long[][] weeklyPrice;
    private Long[][] weeklyQuantity;
    private Long[][] monthlyPrice;
    private Long[][] monthlyQuantity;
    Map<String, Integer> weeklySellers;
    Map<String, Integer> monthlySellers;

    public ItemPageData() {
    }

    public ItemPageData(String itemName, Double price, Integer quantity, Long[][] weeklyPrice, Long[][] weeklyQuantity, Long[][] monthlyPrice, Long[][] monthlyQuantity, Map<String, Integer> weeklySellers, Map<String, Integer> monthlySellers) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.weeklyPrice = weeklyPrice;
        this.weeklyQuantity = weeklyQuantity;
        this.monthlyPrice = monthlyPrice;
        this.monthlyQuantity = monthlyQuantity;
        this.weeklySellers = weeklySellers;
        this.monthlySellers = monthlySellers;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long[][] getWeeklyPrice() {
        return weeklyPrice;
    }

    public void setWeeklyPrice(Long[][] weeklyPrice) {
        this.weeklyPrice = weeklyPrice;
    }

    public Long[][] getWeeklyQuantity() {
        return weeklyQuantity;
    }

    public void setWeeklyQuantity(Long[][] weeklyQuantity) {
        this.weeklyQuantity = weeklyQuantity;
    }

    public Long[][] getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(Long[][] monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

    public Long[][] getMonthlyQuantity() {
        return monthlyQuantity;
    }

    public void setMonthlyQuantity(Long[][] monthlyQuantity) {
        this.monthlyQuantity = monthlyQuantity;
    }

    public Map<String, Integer> getWeeklySellers() {
        return weeklySellers;
    }

    public void setWeeklySellers(Map<String, Integer> weeklySellers) {
        this.weeklySellers = weeklySellers;
    }

    public Map<String, Integer> getMonthlySellers() {
        return monthlySellers;
    }

    public void setMonthlySellers(Map<String, Integer> monthlySellers) {
        this.monthlySellers = monthlySellers;
    }
}
