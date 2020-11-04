package pattern.singleton.register;

/**
 * @author jill
 */
//常量中去使用，常量不就是用来大家都能够共用吗？
//通常在通用API中使用
@SuppressWarnings("ALL")
public enum EnumSingleton {
    /**
     * 实例
     */
    INSTANCE;
    /**
     * 数据
     */
    private Object data;

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
