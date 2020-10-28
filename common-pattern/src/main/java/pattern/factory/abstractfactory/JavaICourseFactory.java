package pattern.factory.abstractfactory;

/**
 * @author Jill Wang
 */
public class JavaICourseFactory implements ICourseFactory {

    @Override
    public INote createNote() {
        return new JavaNote();
    }

    @Override
    public IVideo createVideo() {
        return new JavaVideo();
    }
}
