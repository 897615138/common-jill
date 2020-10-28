package pattern.factory.factorymethod;


import pattern.factory.ICourse;
import pattern.factory.JavaCourse;

/**
 * @author Jill Wang
 */
public class JavaCourseFactory implements ICourseFactory {
    @Override
    public ICourse create() {
        return new JavaCourse();
    }
}
