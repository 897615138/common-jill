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
     * Integer 翻转
     *
     * @param i Integer
     * @return 翻转后的Integer
     */
    public static Integer reverse(Integer i) {
        int rev = 0;
        if (Integer.toString(i).endsWith("0")) {
            System.out.println("last.number.is.zero");
            return 0;
        }
        while (i != 0) {
            //剩下的部分
            int pop = i % 10;
            //加上的数字
            i /= 10;
            //从-2147483648 至 2147483647 ，包括-2147483648 和 2147483647 -2^31~2^31-1
            boolean b = rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7) || rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8);
//            rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > Integer.MAX_VALUE % 10)
            if (b) {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
