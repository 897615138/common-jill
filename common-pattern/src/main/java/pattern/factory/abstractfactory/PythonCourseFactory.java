package pattern.factory.abstractfactory;

/**
 *
 * @author  Jill Wang
 */
public class PythonCourseFactory implements CourseFactory {

    @Override
    public INote createNote() {
        return new PythonNote();
    }


    @Override
    public IVideo createVideo() {
        return new PythonVideo();
    }
}
