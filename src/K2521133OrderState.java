// State pattern. Each order status is its own class and knows the status after it,
// so moving an order forward is just a call to next() and there are no long
// if-else chains on a status field.
public interface K2521133OrderState {
    void next(K2521133Order order);

    String label();

    boolean isFinal();
}
