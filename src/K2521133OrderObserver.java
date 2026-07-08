// Observer pattern. Anything that wants to hear about an order (a status change or
// a promotion) implements this and registers with the order.
public interface K2521133OrderObserver {
    void update(K2521133Order order, String message);
}
