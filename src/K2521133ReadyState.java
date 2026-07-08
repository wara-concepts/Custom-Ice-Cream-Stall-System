public class K2521133ReadyState implements K2521133OrderState {

    @Override
    public void next(K2521133Order order) {
        order.setState(new K2521133DeliveredState());
    }

    @Override
    public String label() {
        return "Ready";
    }

    @Override
    public boolean isFinal() {
        return false;
    }
}
