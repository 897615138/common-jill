package pattern.proxy.dynamic.jdk;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author jill
 */
public
class JDKMeiPo implements InvocationHandler {

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
        //被代理者类加载器 被代理者接口 代理者
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object obj = method.invoke(target, args);
        after();
        return obj;
    }
}
