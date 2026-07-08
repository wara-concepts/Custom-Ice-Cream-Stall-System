// The last status. There is nowhere to move on to, so next() leaves it as is.
public class K2521133DeliveredState implements K2521133OrderState {

    @Override
    public void next(K2521133Order order) {
        // already finished
    }

    @Override
    public String label() {
        return "Delivered";
    }

    @Override
    public boolean isFinal() {
        return true;
    }
}
