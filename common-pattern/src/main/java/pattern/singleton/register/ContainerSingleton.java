package pattern.singleton.register;

import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Spring中的做法，就是用这种注册式单例
 *
 * @author jill
 */
@NoArgsConstructor
public class ContainerSingleton {
    /**
     * ioc 并发的HashMap
     */
    private static final Map<String, Object> IOC = new ConcurrentHashMap<>();

    /**
     * 获得实例
     *
     * @param className 类名
     * @return 对象
     */
    public static Object getInstance(String className) {
        synchronized (IOC) {
            //从HashMap中取出
            //存在就直接返回存的实例
            if (!IOC.containsKey(className)) {
                Object obj = null;
                try {
                    //不存在就存进去再返回
                    obj = Class.forName(className).newInstance();
                    IOC.put(className, obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return obj;
            } else {
                return IOC.get(className);
            }
        }
    }
}
