package jill.common.util.word;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Jill W
 * @date 2020/11/20
 */
public class SimilarUtil {
    /**
     * 返回两个字符串的相似度
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 相似度
     */
    public static float getSimilarityRatio(String str1, String str2) {
        int[][] d;
        int n = str1.length();
        int m = str2.length();
        int i;
        int j;
        char ch1;
        char ch2;
        // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
        int temp;
        if (n == 0 || m == 0) {
            return 0;
        }
        d = new int[n + 1][m + 1];
        // 初始化第一列
        for (i = 0; i <= n; i++) {
            d[i][0] = i;
        }
        // 初始化第一行
        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }
        // 遍历str
        for (i = 1; i <= n; i++) {
            ch1 = str1.charAt(i - 1);
            // 去匹配target
            for (j = 1; j <= m; j++) {
                ch2 = str2.charAt(j - 1);
                if (ch1 == ch2 || ch1 == ch2 + 32 || ch1 + 32 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = Math.min(Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1), d[i - 1][j - 1] + temp);
            }
        }

        return (1 - (float) d[n][m] / Math.max(str1.length(), str2.length())) * 100F;
    }

    public static Set<Integer> getSimilarNum(Integer num, Integer less, Integer more) {
        String s = num.toString();
        HashSet<Integer> integers = new HashSet<>();
        for (int i = less; i <= more; i++) {
            String s1 = Integer.toString(i);
            if (getSimilarityRatio(s, s1) > 0) {
                integers.add(i);
            }
        }
        return integers;
    }

    public static void main(String[] args) {
        Set<Integer> integers = getSimilarNum(0, 0, 100);
        System.out.println(integers);
    }
}
