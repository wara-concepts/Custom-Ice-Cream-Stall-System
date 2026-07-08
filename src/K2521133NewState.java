public class K2521133NewState implements K2521133OrderState {

    @Override
    public void next(K2521133Order order) {
        order.setState(new K2521133PreparingState());
    }

    @Override
    public String label() {
        return "New";
    }

    @Override
    public boolean isFinal() {
        return false;
    }
}
