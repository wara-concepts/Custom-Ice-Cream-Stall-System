// Shared parent for the wrappers. It holds the ice cream being decorated so each
// concrete decorator only has to add its own name and price.
public abstract class K2521133IceCreamDecorator implements K2521133IceCream {
    protected final K2521133IceCream inner;

    protected K2521133IceCreamDecorator(K2521133IceCream inner) {
        this.inner = inner;
    }
}
