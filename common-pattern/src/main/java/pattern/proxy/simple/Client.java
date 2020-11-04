package pattern.proxy.simple;

/**
 * 调用者 简单代理模型
 *
 * @author jill
 */
public class Client {

    public static void main(String[] args) {

        Proxy proxy = new Proxy(new RealSubject());
        proxy.request();

    }

}
