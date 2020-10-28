package pattern.observer.event_bus_guava;

import com.google.common.eventbus.EventBus;

/**
 * @author jill on 2019/3/17.
 */
public class GuavaEventTest {

    public static void main(String[] args) {
        //消息总线 Guava面向方法
        EventBus eventBus = new EventBus();
        GuavaEvent guavaEvent = new GuavaEvent();
        eventBus.register(guavaEvent);
        eventBus.post("jill");
    }
}
