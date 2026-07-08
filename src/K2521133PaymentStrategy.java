// Strategy pattern. Each payment method is a separate class, so the stall can add
// new methods without touching the ordering code. pay() mocks the transaction and
// returns a short receipt line.
public interface K2521133PaymentStrategy {
    String pay(double amount);
}
