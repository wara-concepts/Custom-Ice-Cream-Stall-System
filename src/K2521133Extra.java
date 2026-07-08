public enum K2521133Extra {
    SPRINKLES("Sprinkles", 40.00),
    WHIPPED_CREAM("Whipped cream", 90.00),
    WAFER("Wafer", 50.00);

    private final String label;
    private final double price;

    K2521133Extra(String label, double price) {
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
