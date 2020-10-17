package pattern.decorator.battercake.v2;

/**
 * 煎饼主体类
 * @author jill
 */
public abstract class Pancake {
    /**
     * 获得信息
     * @return 信息
     */
    protected abstract String getMsg();

    /**
     * 货的价格
     * @return 加个
     */
    protected abstract int getPrice();
}
