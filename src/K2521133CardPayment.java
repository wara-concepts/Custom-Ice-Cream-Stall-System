public class K2521133CardPayment implements K2521133PaymentStrategy {
    private final String cardNumber;

    public K2521133CardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String pay(double amount) {
        String masked = cardNumber.length() >= 4
                ? "card ****" + cardNumber.substring(cardNumber.length() - 4)
                : "card";
        return String.format("Paid LKR %,.2f with %s", amount, masked);
    }
}
