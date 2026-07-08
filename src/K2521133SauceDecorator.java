public class K2521133SauceDecorator extends K2521133IceCreamDecorator {
    private final K2521133Sauce sauce;

    public K2521133SauceDecorator(K2521133IceCream inner, K2521133Sauce sauce) {
        super(inner);
        this.sauce = sauce;
    }

    @Override
    public String getDescription() {
        return inner.getDescription() + ", " + sauce.getLabel();
    }

    @Override
    public double getCost() {
        return inner.getCost() + sauce.getPrice();
    }
}
