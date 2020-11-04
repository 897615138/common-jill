package pattern.observer.observable;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者
 *
 * @author jill
 */
public class Teacher implements Observer {
    private final String name;

    Teacher(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        ObserverJ observerJ = (ObserverJ) o;
        Question question = (Question) arg;
        System.out.println("===============================");
        System.out.println(name + "老师，你好！\n" +
                "您收到了一个来自“" + observerJ.getName() + "”的提问，希望您解答，问题内容如下：\n" +
                question.getContent() + "\n" +
                "提问者：" + question.getUserName());
    }
}
