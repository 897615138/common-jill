package pattern.proxy.dynamic_proxy.gp_proxy;


import pattern.proxy.Person;
import pattern.proxy.dynamic_proxy.jdk_proxy.Girl;

/**
 * @author jill
 */
public class GPProxyTest {

    public static void main(String[] args) {
        try {

            //JDK动态代理的实现原理
            Person obj = (Person) new GPMeiPo().getInstance(new Girl());
            System.out.println(obj.getClass());
            try {
                obj.findLove();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
