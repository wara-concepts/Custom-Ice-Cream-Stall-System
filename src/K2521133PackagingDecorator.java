// Optional packaging for a delivery or a gift. Handled as a decorator so it adds
// to the price and the description like any other extra.
public class K2521133PackagingDecorator extends K2521133IceCreamDecorator {
    private final String name;
    private final double price;

    public K2521133PackagingDecorator(K2521133IceCream inner, String name, double price) {
        super(inner);
        this.name = name;
        this.price = price;
    }

    @Override
    public String getDescription() {
        return inner.getDescription() + ", " + name;
    }

    @Override
    public double getCost() {
        return inner.getCost() + price;
    }
}
