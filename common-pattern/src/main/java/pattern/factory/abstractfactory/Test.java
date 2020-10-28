package pattern.factory.abstractfactory;

/**
 * @author Jill Wang
 */
public class Test {

    public static void main(String[] args) {

        JavaICourseFactory factory = new JavaICourseFactory();
        factory.createNote().edit();
        factory.createVideo().record();

        PythonICourseFactory pythonCourseFactory = new PythonICourseFactory();
        pythonCourseFactory.createNote().edit();
        factory.createVideo().record();

    }

}
