package pattern.proxy.dynamic_proxy.gp_proxy;

import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @author JillW
 * @date 2020/10/30
 */


public
class Proxy0 implements pattern.proxy.Person {
    private GPInvocationHandler h;

    public Proxy0(GPInvocationHandler h) {
        this.h = h;
    }

    @Override
    public void findLove() {
        try {
            Method m = pattern.proxy.Person.class.getMethod("findLove");
            h.invoke(this, m, new Object[]{});
        } catch (Error ignored) {
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }
}

