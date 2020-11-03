package pattern.observer.event_bus_guava;

import com.google.common.eventbus.Subscribe;

/**
 * @author jill on 2019/3/17.
 */
public class GuavaEvent {

    @Subscribe
    public void subscribe(String str) {
        System.out.println("执行subscribe方法，传入的参数是：" + str);
    }

}
