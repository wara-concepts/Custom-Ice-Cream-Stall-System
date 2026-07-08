// The plain ice cream before anything is added. Its cost is the base plus the
// flavor, and the decorators build on top of it.
public class K2521133BaseIceCream implements K2521133IceCream {
    private final K2521133Base base;
    private final K2521133Flavor flavor;

    public K2521133BaseIceCream(K2521133Base base, K2521133Flavor flavor) {
        this.base = base;
        this.flavor = flavor;
    }

    @Override
    public String getDescription() {
        return flavor.getLabel() + " in a " + base.getLabel().toLowerCase();
    }

    @Override
    public double getCost() {
        return base.getPrice() + flavor.getPrice();
    }
}
