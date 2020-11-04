package pattern.singleton.lazy;


/**
 * 懒汉式单例
 * 在外部需要使用的时候才进行实例化
 *
 * @author jill
 */
public class LazySimpleSingleton {
    /**
     * 静态块，公共内存区域
     */
    private static LazySimpleSingleton lazy = null;

    private LazySimpleSingleton() {
    }

    public synchronized static LazySimpleSingleton getInstance() {
        if (lazy == null) {
            lazy = new LazySimpleSingleton();
        }
        return lazy;
    }
}
