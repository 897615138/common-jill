package pattern.proxy.simple_proxy;

/**
 * 主体
 *
 * @author jill
 */
public
class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("real service is called.");
    }
}
