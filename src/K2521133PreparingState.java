public class K2521133PreparingState implements K2521133OrderState {

    @Override
    public void next(K2521133Order order) {
        order.setState(new K2521133ReadyState());
    }

    @Override
    public String label() {
        return "Preparing";
    }

    @Override
    public boolean isFinal() {
        return false;
    }
}
