package pattern.proxy.s;

/**
 * 静态代理
 *
 * @author jill
 */
public class FatherProxyTest {

    public static void main(String[] args) {
        Father father = new Father(new Son());
        father.findLove();
    }

}
