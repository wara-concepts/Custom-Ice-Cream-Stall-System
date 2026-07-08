public class K2521133WalletPayment implements K2521133PaymentStrategy {
    private final String walletId;

    public K2521133WalletPayment(String walletId) {
        this.walletId = walletId;
    }

    @Override
    public String pay(double amount) {
        return String.format("Paid LKR %,.2f from wallet %s", amount, walletId);
    }
}
