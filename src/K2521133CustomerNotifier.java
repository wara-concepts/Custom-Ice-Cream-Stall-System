// The customer's side of the Observer pattern. It prints whatever the order sends,
// so the customer sees the status move and any promotions as they happen.
public class K2521133CustomerNotifier implements K2521133OrderObserver {
    private final String customerName;

    public K2521133CustomerNotifier(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public void update(K2521133Order order, String message) {
        System.out.println("  [notify " + customerName + "] Order " + order.getId() + ": " + message);
    }
}
