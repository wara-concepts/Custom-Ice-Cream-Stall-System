public class K2521133NoDiscount implements K2521133DiscountStrategy {

    @Override
    public double applyDiscount(double amount) {
        return amount;
    }

    @Override
    public String label() {
        return "No discount";
    }
}
