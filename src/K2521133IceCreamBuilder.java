// Builder pattern. The customer picks a base and flavor, then adds as many
// toppings, sauces and extras as they like. build() wraps the plain ice cream in
// one decorator per choice and hands back the finished item.
public class K2521133IceCreamBuilder {
    private K2521133Base base;
    private K2521133Flavor flavor;
    private final java.util.List<K2521133Topping> toppings = new java.util.ArrayList<>();
    private final java.util.List<K2521133Sauce> sauces = new java.util.ArrayList<>();
    private final java.util.List<K2521133Extra> extras = new java.util.ArrayList<>();
    private String packagingName;
    private double packagingPrice;

    public K2521133IceCreamBuilder setBase(K2521133Base base) {
        this.base = base;
        return this;
    }

    public K2521133IceCreamBuilder setFlavor(K2521133Flavor flavor) {
        this.flavor = flavor;
        return this;
    }

    public K2521133IceCreamBuilder addTopping(K2521133Topping topping) {
        toppings.add(topping);
        return this;
    }

    public K2521133IceCreamBuilder addSauce(K2521133Sauce sauce) {
        sauces.add(sauce);
        return this;
    }

    public K2521133IceCreamBuilder addExtra(K2521133Extra extra) {
        extras.add(extra);
        return this;
    }

    public K2521133IceCreamBuilder withPackaging(String name, double price) {
        this.packagingName = name;
        this.packagingPrice = price;
        return this;
    }

    public K2521133IceCream build() {
        if (base == null || flavor == null) {
            throw new IllegalStateException("An ice cream needs a base and a flavor.");
        }

        K2521133IceCream iceCream = new K2521133BaseIceCream(base, flavor);
        for (K2521133Topping topping : toppings) {
            iceCream = new K2521133ToppingDecorator(iceCream, topping);
        }
        for (K2521133Sauce sauce : sauces) {
            iceCream = new K2521133SauceDecorator(iceCream, sauce);
        }
        for (K2521133Extra extra : extras) {
            iceCream = new K2521133ExtraDecorator(iceCream, extra);
        }
        if (packagingName != null) {
            iceCream = new K2521133PackagingDecorator(iceCream, packagingName, packagingPrice);
        }
        return iceCream;
    }
}
