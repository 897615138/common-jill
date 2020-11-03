package pattern.template.course;

/**
 * 模板会有一个或者多个未现实方法，
 * 而且这几个未实现方法有固定的执行循序
 *
 * @author jill
 */
public abstract class AbstractNetworkCourse {

    private static void postSource() {
        System.out.println("提交源代码");
    }

    private static void postNote() {
        System.out.println("提交课件和笔记");
    }

    private static void liveVideo() {
        System.out.println("直播授课");
    }

    private static void createP() {
        System.out.println("创建备课PPT");
    }

    private static void postPreResource() {
        System.out.println("分发预习资料");
    }

    final void createCourse() {
        //1、发布预习资料
        postPreResource();

        //2、制作PPT课件
        createP();

        //3、在线直播
        liveVideo();

        //4、提交课件、课堂笔记
        postNote();

        //5、提交源码
        postSource();

        //6、布置作业，有些课是没有作业，有些课是有作业的 如果有作业的话，检查作业，如果没作业，完成了
        if (needHomework()) checkHomework();
    }

    abstract void checkHomework();

    /**
     * 钩子方法：实现流程的微调
     *
     * @return Boolean
     */
    protected boolean needHomework() {
        return false;
    }

}
