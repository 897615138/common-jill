package pattern.proxy.db.proxy;


import pattern.proxy.db.IOrderService;
import pattern.proxy.db.Order;
import pattern.proxy.db.db.DynamicDataSourceEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jill
 */
public class OrderServiceStaticProxyImpl implements IOrderService {
    private final SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

    private final IOrderService orderService;

    public OrderServiceStaticProxyImpl(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public int createOrder(Order order) {
        Long time = order.getCreateTime();
        int dbRouter = Integer.parseInt(yearFormat.format(new Date(time)));
        System.out.println("静态代理类自动分配到【DB_" + dbRouter + "】数据源处理数据");
        DynamicDataSourceEntity.set(dbRouter);

        orderService.createOrder(order);
        DynamicDataSourceEntity.restore();

        return 0;
    }
}
