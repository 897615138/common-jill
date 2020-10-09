package pattern.factory.abstractfactory;

/**
 *
 * @author  Jill Wang
 */
public class JavaCourseFactory implements CourseFactory {

    public INote createNote() {
        return new JavaNote();
    }

    public IVideo createVideo() {
        return new JavaVideo();
    }
}
