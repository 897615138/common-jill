package pattern.template.course;

/**
 * @author jill
 */
public class BigDataCourseAbstract extends AbstractNetworkCourse {

    private final boolean needHomeworkFlag;

    BigDataCourseAbstract() {
        needHomeworkFlag = true;
    }

    @Override
    void checkHomework() {
        System.out.println("检查大数据的课后作业");
    }

    @Override
    protected boolean needHomework() {
        return needHomeworkFlag;
    }
}
