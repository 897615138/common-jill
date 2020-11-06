package jill.common.util.number;

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

    /**
     * 打印99乘法表
     *
     * @param end 打到多少为止
     */
    public static void printMultiplication(int end) {
        for (int i = 1; i <= end; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "*" + i + '=' + i * j + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 判断是否为素数，是否可以分解质因数 是为1
     *
     * @param num 数字
     */
    public boolean primeJudge(int num) {
        //判断（是否为素数）
        return num % 6 == 1 || num % 6 == 5 || num < 4;
    }

    public Integer countPrimeNumbers(Integer start, Integer end) {
        Integer count = 0;
        for (int i = start; i <= end; i++) {
            if (primeJudge(i)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 尾递归 n的阶乘
     *
     * @param n   还有多少次计算
     * @param res 阶乘的结果
     * @return n的阶乘
     */
    public static int factorial3(int n, int res) {
        if (n == 1) {
            return res;
        }
        return factorial3(n - 1, n * res);
    }
}
