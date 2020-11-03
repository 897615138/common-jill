package pattern.observer.event_listenor.core;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 监听器，它就是观察者
 *
 * @author jill
 */
public class EventListener {

    /**
     * JDK底层的Listener通常也是这样来设计的
     */
    protected Map<String, Event> events = new HashMap<>();

    /**
     * 事件名称和一个目标对象来触发事件
     *
     * @param eventType 事件类型
     * @param target    触发对象
     */
    public void addListener(String eventType, Object target) {
        try {
            this.addListener(
                    eventType,
                    target,
                    target.getClass().getMethod("on" + toUpperFirstCase(eventType), Event.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addListener(String eventType, Object target, Method callback) {
        //注册事件
        events.put(eventType, new Event(target, callback));
    }


    /**
     * 触发，只要有动作就触发
     *
     * @param event 时间
     */
    private void trigger(Event event) {
        event.setSource(this);
        event.setTime(System.currentTimeMillis());

        try {
            //发起回调
            if (event.getCallback() != null) {
                //用反射调用它的回调函数
                event.getCallback().invoke(event.getTarget(), event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 事件名称触发
     *
     * @param trigger 触发器
     */
    protected void trigger(String trigger) {
        if (!this.events.containsKey(trigger)) {
            return;
        }
        trigger(this.events.get(trigger).setTrigger(trigger));
    }

    /**
     * 逻辑处理的私有方法，首字母大写
     *
     * @param str 字符串
     * @return 首字母大写
     */
    private String toUpperFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] -= 32;
        return String.valueOf(chars);
    }

}
