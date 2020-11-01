package pattern.proxy.db_route.proxy;


import pattern.proxy.db_route.IOrderService;
import pattern.proxy.db_route.Order;
import pattern.proxy.db_route.db.DynamicDataSourceEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jill on 2019/3/10.
 */
public class OrderServiceStaticProxy implements IOrderService {
    private final SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

    private final IOrderService orderService;

    public OrderServiceStaticProxy(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Override public int createOrder(Order order) {
        Long    time     = order.getCreateTime();
        Integer dbRouter = Integer.valueOf(yearFormat.format(new Date(time)));
        System.out.println("静态代理类自动分配到【DB_" + dbRouter + "】数据源处理数据");
        DynamicDataSourceEntity.set(dbRouter);

        orderService.createOrder(order);
        DynamicDataSourceEntity.restore();

        return 0;
    }
}
