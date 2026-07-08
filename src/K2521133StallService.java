import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Holds every order and runs the stall's rules: creating an order, moving its
// status forward, taking payment through the chosen strategies, and storing
// feedback. Orders live in memory in a map keyed by the order id.
public class K2521133StallService {

    private final Map<String, K2521133Order> orders = new LinkedHashMap<>();
    private int nextId = 1;

    public K2521133Order createOrder(String customerName, K2521133ServingMethod servingMethod) {
        String id = String.format("ORD%03d", nextId++);
        K2521133Order order = new K2521133Order(id, customerName, servingMethod);
        order.addObserver(new K2521133CustomerNotifier(customerName));
        orders.put(id, order);
        return order;
    }

    public K2521133Order find(String id) {
        return orders.get(id);
    }

    public boolean advance(String id) {
        K2521133Order order = require(id);
        return order.advance();
    }

    // Applies the promotion first, then runs the payment method. Both are passed
    // in, so the service does not care which discount or method was chosen.
    public String pay(String id, K2521133DiscountStrategy discount, K2521133PaymentStrategy payment) {
        K2521133Order order = require(id);
        if (order.getItems().isEmpty()) {
            throw new IllegalStateException("Order " + id + " has no items to pay for.");
        }
        if (order.isPaid()) {
            throw new IllegalStateException("Order " + id + " is already paid.");
        }

        double payable = discount.applyDiscount(order.getTotal());
        String receipt = payment.pay(payable);
        order.markPaid(payable);
        return discount.label() + ". " + receipt;
    }

    public void recordFeedback(String id, int rating, String comment) {
        K2521133Order order = require(id);
        order.setFeedback(new K2521133Feedback(rating, comment));
    }

    public void broadcastPromotion(String message) {
        for (K2521133Order order : orders.values()) {
            order.notifyPromotion(message);
        }
    }

    public List<K2521133Order> allOrders() {
        return new ArrayList<>(orders.values());
    }

    private K2521133Order require(String id) {
        K2521133Order order = orders.get(id);
        if (order == null) {
            throw new IllegalArgumentException("No order with id " + id);
        }
        return order;
    }
}
