package pattern.proxy.simple_proxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 代理对象
 *
 * @author jill
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public
class Proxy implements Subject {

    private Subject subject;

    private static void before() {
        System.out.println("called before request().");
    }

    private static void after() {
        System.out.println("called after request().");
    }

    @Override
    public void request() {
        before();
        subject.request();
        after();
    }
}
