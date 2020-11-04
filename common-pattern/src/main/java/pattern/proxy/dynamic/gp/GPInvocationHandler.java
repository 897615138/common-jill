package pattern.proxy.dynamic.gp;

import java.lang.reflect.Method;

/**
 * @author jill
 */
public
interface GPInvocationHandler {
    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
