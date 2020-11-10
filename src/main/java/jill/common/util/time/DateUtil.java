package jill.common.util.time;


/**
 * @author Jill W
 * @date 2020/11/06
 */
public class DateUtil {
    /**
     * 闰年判断
     */
    public static String isLeapYear(Integer year) {
        //能被4整除且不能被100整除的为普通闰年，能被400整除的为世纪闰年
        if (year % 400 == 0) {
            return (year + "是世纪闰年");
        } else {
            if (year % 4 == 0 && year % 100 != 0) {
                return (year + "是普通闰年");
            } else {
                return (year + "是平年");
            }
        }
    }

}
