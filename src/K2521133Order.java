import java.util.ArrayList;
import java.util.List;

// One customer order. It is the subject in the Observer pattern and the context in
// the State pattern: it keeps the current state, moves it forward, and tells its
// observers what happened.
public class K2521133Order {
    private final String id;
    private final String customerName;
    private final List<K2521133OrderItem> items = new ArrayList<>();
    private final List<K2521133OrderObserver> observers = new ArrayList<>();
    private K2521133ServingMethod servingMethod;
    private K2521133OrderState state = new K2521133NewState();
    private boolean paid;
    private double amountPaid;
    private K2521133Feedback feedback;

    public K2521133Order(String id, String customerName, K2521133ServingMethod servingMethod) {
        this.id = id;
        this.customerName = customerName;
        this.servingMethod = servingMethod;
    }

    public String getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public K2521133ServingMethod getServingMethod() {
        return servingMethod;
    }

    public List<K2521133OrderItem> getItems() {
        return items;
    }

    public void addItem(K2521133OrderItem item) {
        items.add(item);
    }

    public double getTotal() {
        double total = 0;
        for (K2521133OrderItem item : items) {
            total += item.getLineCost();
        }
        return total;
    }

    public void addObserver(K2521133OrderObserver observer) {
        observers.add(observer);
    }

    // Moves the order to the next status and lets every observer know. Returns
    // false when the order is already delivered.
    public boolean advance() {
        if (state.isFinal()) {
            return false;
        }
        state.next(this);
        notifyObservers("status is now " + state.label());
        return true;
    }

    public void notifyPromotion(String message) {
        notifyObservers(message);
    }

    private void notifyObservers(String message) {
        for (K2521133OrderObserver observer : observers) {
            observer.update(this, message);
        }
    }

    public void setState(K2521133OrderState state) {
        this.state = state;
    }

    public String getStatusLabel() {
        return state.label();
    }

    public boolean isPaid() {
        return paid;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void markPaid(double amount) {
        this.paid = true;
        this.amountPaid = amount;
    }

    public K2521133Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(K2521133Feedback feedback) {
        this.feedback = feedback;
    }
}
