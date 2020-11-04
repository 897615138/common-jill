package pattern.proxy.dynamic.gp;


import pattern.proxy.Person;
import pattern.proxy.dynamic.jdk.Girl;

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
