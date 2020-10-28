package pattern.factory.abstractfactory;

/**
 * @author Jill Wang
 */
public class PythonICourseFactory implements ICourseFactory {

    @Override
    public INote createNote() {
        return new PythonNote();
    }


    @Override
    public IVideo createVideo() {
        return new PythonVideo();
    }
}
