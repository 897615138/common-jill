package pattern.template.course;

/**
 * @author jill
 */
public class NetworkCourseTest {
    public static void main(String[] args) {

        System.out.println("---Java架构师课程---");
        AbstractNetworkCourse javaCourse = new JavaCourseAbstract();
        javaCourse.createCourse();

        System.out.println("---大数据课程---");
        AbstractNetworkCourse bigDataCourse = new BigDataCourseAbstract(true);
        bigDataCourse.createCourse();

    }
}
