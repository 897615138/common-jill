package pattern.proxy.db_route;

/**
 * @author jill
 */
public class OrderService implements IOrderService {

    /**
     * @Autowired
     */
    private OrderDao orderDao;

    /**
     * OrderService() {
     * //如果使用Spring应该是自动注入的
     * //我们为了使用方便，在构造方法中将orderDao直接初始化了
     * orderDao = new OrderDao();
     * }
     *
     * @param order order
     * @return return
     */

    @Override
    public int createOrder(Order order) {
        OrderDao.createOrder(order);
        System.out.println("OrderService调用orderDao创建订单");
        return OrderDao.insert(order);
    }
}
