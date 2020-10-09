package pattern.factory.simplefactory;


import pattern.factory.ICourse;
import pattern.factory.JavaCourse;

/**
 * 小作坊式的工厂模型
 *
 * @author  Jill Wang
 */
public class SimpleFactoryTest {

    public static void main(String[] args) {

//        ICourse course = new JavaCourse();
//        course.record();

//        ICourseFactory factory = new ICourseFactory();
//        ICourse course = factory.create("com.gupaoedu.vip.pattern.factory.JavaCourse");
//        course.record();

        CourseFactory factory = new CourseFactory();
        ICourse course = factory.create(JavaCourse.class);
        course.record();

    }
}
