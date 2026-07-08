public class K2521133QrPayment implements K2521133PaymentStrategy {

    @Override
    public String pay(double amount) {
        return String.format("Paid LKR %,.2f by scanning the stall QR code", amount);
    }
}
