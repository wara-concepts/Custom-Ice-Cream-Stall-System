// A second Strategy, this time for promotions. The stall swaps the discount rule
// without changing how an order is priced or paid.
public interface K2521133DiscountStrategy {
    double applyDiscount(double amount);

    String label();
}
