public class K2521133ExtraDecorator extends K2521133IceCreamDecorator {
    private final K2521133Extra extra;

    public K2521133ExtraDecorator(K2521133IceCream inner, K2521133Extra extra) {
        super(inner);
        this.extra = extra;
    }

    @Override
    public String getDescription() {
        return inner.getDescription() + ", " + extra.getLabel();
    }

    @Override
    public double getCost() {
        return inner.getCost() + extra.getPrice();
    }
}
