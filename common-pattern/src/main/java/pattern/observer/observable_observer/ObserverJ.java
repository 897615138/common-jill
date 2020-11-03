package pattern.observer.observable_observer;

import java.util.Observable;

/**
 * JDK提供的一种观察者的实现方式，被观察者
 *
 * @author jill
 */
public class ObserverJ extends Observable {

    private static ObserverJ observerJ = null;
    private final String name = "Observer生态圈";

    private ObserverJ() {
    }

    /**
     * 单例模式
     *
     * @return 获取对象
     */
    public static ObserverJ getInstance() {
        if (null == observerJ) observerJ = new ObserverJ();
        return observerJ;
    }

    public String getName() {
        return name;
    }

    void publishQuestion(Question question) {
        System.out.println(question.getUserName() + "在" + name + "上提交了一个问题。");
        setChanged();
        notifyObservers(question);
    }
}
