public class K2521133ToppingDecorator extends K2521133IceCreamDecorator {
    private final K2521133Topping topping;

    public K2521133ToppingDecorator(K2521133IceCream inner, K2521133Topping topping) {
        super(inner);
        this.topping = topping;
    }

    @Override
    public String getDescription() {
        return inner.getDescription() + ", " + topping.getLabel();
    }

    @Override
    public double getCost() {
        return inner.getCost() + topping.getPrice();
    }
}
