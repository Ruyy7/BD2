package unlp.info.bd2.dto;

public class RoutePurchaseSummary {
    private String routeName;
    private Long totalPurchases;
    private float averagePrice;

    public RoutePurchaseSummary(String routeName, Long totalPurchases, float averagePrice) {
        this.routeName = routeName;
        this.totalPurchases = totalPurchases;
        this.averagePrice = averagePrice;
    }

    public String getRouteName() {
        return routeName;
    }

    public Long getTotalPurchases() {
        return totalPurchases;
    }

    public float getAveragePrice() {
        return averagePrice;
    }
}
