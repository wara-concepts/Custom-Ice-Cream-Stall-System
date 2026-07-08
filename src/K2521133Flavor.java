public enum K2521133Flavor {
    VANILLA("Vanilla", 200.00),
    CHOCOLATE("Chocolate", 250.00),
    STRAWBERRY("Strawberry", 220.00),
    MANGO("Mango", 240.00);

    private final String label;
    private final double price;

    K2521133Flavor(String label, double price) {
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
