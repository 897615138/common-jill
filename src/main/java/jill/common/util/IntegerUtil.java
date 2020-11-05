package jill.common.util;

/**
 * Integer工具类
 *
 * @author Jill W
 * @date 2020/11/05
 */
public class IntegerUtil {
    /**
     * Integer -> Character
     *
     * @param i Integer
     * @return Character
     */
    public static Character integerToChar(Integer i) {
        return (char) (i + 48);
    }

    /**
     * TODO
     * Integer 翻转
     *
     * @param i Integer
     * @return 翻转后的Integer
     */
    public static Integer reverse(Integer i) {
        int rev = 0;
        while (i != 0) {
            int pop = i % 10;
            i /= 10;
            boolean b = rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7);
            if (b) {
                return 0;
            }
            b = rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8);
            if (b) {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
