public enum K2521133Sauce {
    CHOCOLATE("Chocolate sauce", 60.00),
    CARAMEL("Caramel sauce", 70.00),
    STRAWBERRY("Strawberry sauce", 60.00);

    private final String label;
    private final double price;

    K2521133Sauce(String label, double price) {
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
