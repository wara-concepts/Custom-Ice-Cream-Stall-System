public enum K2521133ServingMethod {
    PICKUP("Pickup"),
    DELIVERY("Delivery");

    private final String label;

    K2521133ServingMethod(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
