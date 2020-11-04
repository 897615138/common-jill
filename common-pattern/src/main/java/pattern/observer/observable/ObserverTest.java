package pattern.observer.observable;

/**
 * @author jill
 */
public class ObserverTest {
    public static void main(String[] args) {
        //获得对象
        ObserverJ observerJ = ObserverJ.getInstance();
        //添加观察者
        Teacher tom = new Teacher("Tom");
        Teacher mic = new Teacher("Mic");
        observerJ.addObserver(tom);
        observerJ.addObserver(mic);

        //被观察者做事情
        Question question = new Question();
        question.setUserName("小明");
        question.setContent("观察者设计模式适用于哪些场景？");
        observerJ.publishQuestion(question);
    }

}
