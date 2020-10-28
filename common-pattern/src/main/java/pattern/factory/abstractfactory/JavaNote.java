package pattern.factory.abstractfactory;

/**
 * Java笔记
 *
 * @author Jill Wang
 */
public class JavaNote implements INote {

    @Override
    public void edit() {
        System.out.println("编写Java笔记");
    }
}
