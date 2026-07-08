public enum K2521133Topping {
    NUTS("Nuts", 80.00),
    CHOCO_CHIPS("Choco chips", 70.00),
    FRUIT("Fruit", 80.00);

    private final String label;
    private final double price;

    K2521133Topping(String label, double price) {
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
