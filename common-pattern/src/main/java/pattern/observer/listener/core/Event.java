package pattern.observer.listener.core;

import java.lang.reflect.Method;

/**
 * 监听器的一种包装,标准事件源格式的定义
 *
 * @author jill
 */
public class Event {
    /**
     * 事件触发，要通知谁
     */
    private final Object target;
    /**
     * 事件触发，要做什么动作，回调
     */
    private final Method callback;
    /**
     * 事件源，事件是由谁发起的保存起来
     */
    private Object source;
    /**
     * 事件的名称，触发的是什么事件
     */
    private String trigger;
    /**
     * 事件触发的时间
     */
    private long time;

    Event(Object target, Method callback) {
        this.target = target;
        this.callback = callback;
    }

    public Object getSource() {
        return source;
    }

    public Event setSource(Object source) {
        this.source = source;
        return this;
    }

    Event setTrigger(String trigger) {
        this.trigger = trigger;
        return this;
    }

    public long getTime() {
        return time;
    }

    void setTime(long time) {
        this.time = time;
    }

    Object getTarget() {
        return target;
    }

    Method getCallback() {
        return callback;
    }

    @Override
    public String toString() {
        return "Event{" + "\n" +
                "\tsource=" + source.getClass() + ",\n" +
                "\ttarget=" + target.getClass() + ",\n" +
                "\tcallback=" + callback + ",\n" +
                "\ttrigger='" + trigger + "',\n" +
                "\ttime=" + time + "'\n" +
                '}';
    }
}
