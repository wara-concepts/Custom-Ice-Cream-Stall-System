public class K2521133PercentageDiscount implements K2521133DiscountStrategy {
    private final double percent;

    public K2521133PercentageDiscount(double percent) {
        this.percent = percent;
    }

    @Override
    public double applyDiscount(double amount) {
        return amount * (1 - percent / 100);
    }

    @Override
    public String label() {
        return ((int) percent) + "% promotion";
    }
}
