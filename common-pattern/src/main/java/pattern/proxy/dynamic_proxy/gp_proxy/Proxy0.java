package pattern.proxy.dynamic_proxy.gp_proxy;

import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @author JillW
 * @date 2020/10/30
 */


public
class Proxy0 implements pattern.proxy.Person {
    private final GPInvocationHandler h;

    public Proxy0(GPInvocationHandler h) {
        this.h = h;
    }

    @Override
    public void findLove() throws Throwable {
        try {
            Method m = pattern.proxy.Person.class.getMethod("findLove");
            h.invoke(this, m, new Object[]{});
        } catch (Exception throwable) {
            throwable.printStackTrace();
            throw new UndeclaredThrowableException(throwable);
        }
    }
}

