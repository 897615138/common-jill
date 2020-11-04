package pattern.proxy.db;

/**
 * @author jill
 */
@SuppressWarnings("ALL")
public class OrderDao {
    public static int insert(Order order) {
        System.out.println("OrderDao创建Order成功!" + order);
        return 1;
    }

    static void createOrder(Order order) {
        System.out.println(order);
    }
}
