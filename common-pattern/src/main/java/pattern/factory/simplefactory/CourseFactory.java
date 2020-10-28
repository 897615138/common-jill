package pattern.factory.simplefactory;


import pattern.factory.ICourse;

/**
 * @author Jill Wang
 */
public class CourseFactory {
    public ICourse create(Class<? extends ICourse> clazz) {
        try {
            if (null != clazz) {
                return clazz.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
