package home;

public class Listing {
    private String itemName;
    private String userName;
    private Integer numStacks;
    private Integer numPerStack;
    private Integer totalBid;
    private Integer totalBuyout;
    private Integer unitBuyout;
    private Long date;

    public Listing(String itemName, String userName, Integer numStacks, Integer numPerStack, Integer totalBid, Integer totalBuyout, Integer unitBuyout, Long date) {
        this.itemName = itemName;
        this.userName = userName;
        this.numStacks = numStacks;
        this.numPerStack = numPerStack;
        this.totalBid = totalBid;
        this.totalBuyout = totalBuyout;
        this.unitBuyout = unitBuyout;
        this.date = date;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getNumStacks() {
        return numStacks;
    }

    public void setNumStacks(Integer numStacks) {
        this.numStacks = numStacks;
    }

    public Integer getNumPerStack() {
        return numPerStack;
    }

    public void setNumPerStack(Integer numPerStack) {
        this.numPerStack = numPerStack;
    }

    public Integer getTotalBid() {
        return totalBid;
    }

    public void setTotalBid(Integer totalBid) {
        this.totalBid = totalBid;
    }

    public Integer getTotalBuyout() {
        return totalBuyout;
    }

    public void setTotalBuyout(Integer totalBuyout) {
        this.totalBuyout = totalBuyout;
    }

    public Integer getUnitBuyout() {
        return unitBuyout;
    }

    public void setUnitBuyout(Integer unitBuyout) {
        this.unitBuyout = unitBuyout;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Listing{" +
                "itemName='" + itemName + '\'' +
                ", userName='" + userName + '\'' +
                ", numStacks=" + numStacks +
                ", numPerStack=" + numPerStack +
                ", totalBid=" + totalBid +
                ", totalBuyout=" + totalBuyout +
                ", unitBuyout=" + unitBuyout +
                ", date=" + date +
                '}';
    }
}
