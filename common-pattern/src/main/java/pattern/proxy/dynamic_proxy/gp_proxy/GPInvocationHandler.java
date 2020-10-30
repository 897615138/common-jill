package pattern.proxy.dynamic_proxy.gp_proxy;

import java.lang.reflect.Method;

/**
 * @author jill
 */
public
interface GPInvocationHandler {
    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
