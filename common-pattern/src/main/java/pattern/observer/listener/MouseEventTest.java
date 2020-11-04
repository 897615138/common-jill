package pattern.observer.listener;

import pattern.observer.listener.mouse_event.Mouse;
import pattern.observer.listener.mouse_event.MouseEventCallback;
import pattern.observer.listener.mouse_event.MouseEventType;

/**
 * @author jill
 */
public class MouseEventTest {
    public static void main(String[] args) {

        MouseEventCallback callback = new MouseEventCallback();

        Mouse mouse = new Mouse();

        //@谁？  @回调方法
        mouse.addListener(MouseEventType.ON_CLICK, callback);
        mouse.addListener(MouseEventType.ON_FOCUS, callback);

        mouse.click();
        mouse.focus();
    }
}
