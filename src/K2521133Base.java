// The container the ice cream is served in. Each base has its own price.
public enum K2521133Base {
    CONE("Cone", 150.00),
    CUP("Cup", 120.00);

    private final String label;
    private final double price;

    K2521133Base(String label, double price) {
        this.label = label;
        this.price = price;
    }

    public String getLabel() {
        return label;
    }

    public double getPrice() {
        return price;
    }
}
