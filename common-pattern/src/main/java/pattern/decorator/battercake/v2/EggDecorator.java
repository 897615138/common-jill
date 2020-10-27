package pattern.decorator.battercake.v2;

/**
 * Created by Tom on 2019/3/17.
 */
public class EggDecorator extends AbstractPancakeDecorator {
    public EggDecorator(AbstractPancake pancake) {
        super(pancake);
    }

    /**
     * 附加的方法
     */
    @Override
    protected void doSomething() {

    }

    @Override
    protected String getMsg() {
        return super.getMsg() + "+1个鸡蛋";
    }

    @Override
    protected int getPrice() {
        return super.getPrice() + 1;
    }
}
