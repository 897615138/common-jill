package pattern.decorator.battercake.v2;

/**
 * @author jill
 */
public class SausageDecorator extends AbstractPancakeDecorator {
    public SausageDecorator(AbstractPancake pancake) {
        super(pancake);
    }

    @Override
    protected void doSomething() {
    }

    @Override
    protected String getMsg() {
        return super.getMsg() + "+1根香肠";
    }

    @Override
    protected int getPrice() {
        return super.getPrice() + 2;
    }
}
