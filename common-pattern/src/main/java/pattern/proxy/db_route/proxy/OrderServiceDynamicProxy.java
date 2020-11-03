package pattern.proxy.db_route.proxy;


import pattern.proxy.db_route.db.DynamicDataSourceEntity;
import pattern.proxy.dynamic_proxy.gp_proxy.GPClassLoader;
import pattern.proxy.dynamic_proxy.gp_proxy.GPInvocationHandler;
import pattern.proxy.dynamic_proxy.gp_proxy.GPProxy;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jill on 2019/3/10.
 */
public
class OrderServiceDynamicProxy implements GPInvocationHandler {

    private final SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    private Object proxyObj;

    private static void after() {
        System.out.println("Proxy after method");
        //还原成默认的数据源
        DynamicDataSourceEntity.restore();
    }

    public Object getInstance(Object proxyObj) {
        this.proxyObj = proxyObj;
        Class<?> clazz = proxyObj.getClass();
        return GPProxy.newProxyInstance(new GPClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before(args[0]);
        Object object = method.invoke(proxyObj, args);
        after();
        return object;
    }

    //target 应该是订单对象Order
    private void before(Object target) {
        try {
            //进行数据源的切换
            System.out.println("Proxy before method");

            //约定优于配置
            Long time = (Long) target.getClass().getMethod("getCreateTime").invoke(target);
            int dbRouter = Integer.parseInt(yearFormat.format(new Date(time)));
            System.out.println("静态代理类自动分配到【DB_" + dbRouter + "】数据源处理数据");
            DynamicDataSourceEntity.set(dbRouter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
