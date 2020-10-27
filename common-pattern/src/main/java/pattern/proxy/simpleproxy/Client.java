package pattern.proxy.simpleproxy;

/**
 * Created by jill.
 */
public class Client {

    public static void main(String[] args) {

        Proxy proxy = new Proxy(new RealSubject());
        proxy.request();

    }

}
