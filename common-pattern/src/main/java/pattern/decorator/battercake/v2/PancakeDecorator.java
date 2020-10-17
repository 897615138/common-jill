package pattern.decorator.battercake.v2;

/**
 * @author jill
 */
public abstract class PancakeDecorator extends Pancake {
    /**
     * 静态代理 委派
     */
    private final Pancake pancake;

    public PancakeDecorator(Pancake pancake) {
        this.pancake = pancake;
    }

    /**
     * 附加的方法
     */
    protected abstract void doSomething();

    @Override
    protected String getMsg() {
        return this.pancake.getMsg();
    }

    @Override
    protected int getPrice() {
        return this.pancake.getPrice();
    }
}
