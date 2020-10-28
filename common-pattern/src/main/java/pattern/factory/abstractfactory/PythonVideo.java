package pattern.factory.abstractfactory;

/**
 * @author Jill Wang
 */
public class PythonVideo implements IVideo {
    @Override
    public void record() {
        System.out.println("录制Python视频");
    }
}
