package pattern.proxy.dynamic_proxy.gp_proxy;


import java.lang.reflect.Method;

/**
 * @author jill
 */
public
class GPMeiPo implements GPInvocationHandler {
    private Object target;

    private static void before() {
        System.out.println("我是媒婆，我要给你找对象，现在已经确认你的需求");
        System.out.println("开始物色");
    }

    private static void after() {
        System.out.println("OK的话，准备办事");
    }

    public Object getInstance(Object target) {
        this.target = target;
        Class<?> clazz = target.getClass();
        //被代理者的类加载器 被代理者的接口 当前对象
        return GPProxy.newProxyInstance(new GPClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object obj = method.invoke(target, args);
        after();
        return obj;
    }
}
