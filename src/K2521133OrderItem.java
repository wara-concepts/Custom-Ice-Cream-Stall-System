// One line on the order: a finished ice cream and how many of it the customer wants.
public class K2521133OrderItem {
    private final K2521133IceCream iceCream;
    private final int quantity;

    public K2521133OrderItem(K2521133IceCream iceCream, int quantity) {
        this.iceCream = iceCream;
        this.quantity = quantity;
    }

    public K2521133IceCream getIceCream() {
        return iceCream;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getLineCost() {
        return iceCream.getCost() * quantity;
    }
}
