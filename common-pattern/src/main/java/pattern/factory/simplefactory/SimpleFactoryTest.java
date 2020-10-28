package pattern.factory.simplefactory;


import pattern.factory.ICourse;
import pattern.factory.JavaCourse;

/**
 * 小作坊式的工厂模型
 *
 * @author Jill Wang
 */
public class SimpleFactoryTest {

    public static void main(String[] args) {
        CourseFactory factory = new CourseFactory();
        ICourse course = factory.create(JavaCourse.class);
        course.record();
    }
}
