package pattern.proxy.dynamic.jdk;


import java.lang.reflect.Method;

/**
 * @author jill
 */
public class JDKProxyTest {

    public static void main(String[] args) {
        try {
            Object obj = new JDKMeiPo().getInstance(new Girl());
            Method method = obj.getClass().getMethod("findLove");
            method.invoke(obj);

            //obj.findLove();

            //$Proxy0
//            byte [] bytes = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{Person.class});
//            FileOutputStream os = new FileOutputStream("E://$Proxy0.class");
//            os.write(bytes);
//            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
