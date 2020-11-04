package pattern.proxy.db.db;

/**
 * @author jill on 2019/3/10.
 */
public class DynamicDataSourceEntity {

    private final static String DEFAULT_SOURCE = null;

    private final static ThreadLocal<String> LOCAL = new ThreadLocal<>();

    private DynamicDataSourceEntity() {
    }


    public static String get() {
        return LOCAL.get();
    }

    public static void restore() {
        LOCAL.set(DEFAULT_SOURCE);
    }

    public static void set(String source) {
        LOCAL.set(source);
    }

    public static void set(int year) {
        LOCAL.set("DB_" + year);
    }

    public static void removeThreadLocal(){
        LOCAL.remove();
    }

}
