package pattern.factory.abstractfactory;


/**
 * @author Jill Wang
 */
public class JavaVideo implements IVideo {
    @Override
    public void record() {
        System.out.println("录制Java视频");
    }
}
