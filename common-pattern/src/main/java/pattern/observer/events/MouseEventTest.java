package pattern.observer.events;


import pattern.observer.events.mouseevent.Mouse;
import pattern.observer.events.mouseevent.MouseEventCallback;
import pattern.observer.events.mouseevent.MouseEventType;

/**
 * Created by jill on 2019/3/17.
 */
public class MouseEventTest {
    public static void main(String[] args) {

        MouseEventCallback callback = new MouseEventCallback();

        Mouse mouse = new Mouse();

        //@谁？  @回调方法
        mouse.addLisenter(MouseEventType.ON_CLICK,callback);
        mouse.addLisenter(MouseEventType.ON_FOCUS,callback);

        mouse.click();

        mouse.focus();


    }
}
