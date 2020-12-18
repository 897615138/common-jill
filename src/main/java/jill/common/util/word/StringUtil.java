package jill.common.util.word;

import jill.common.model.util.StringResult;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author JillW
 * @date 2020/09/30
 * 字符串工具
 */
public class StringUtil {
    /**
     * 字符是否都唯一
     *
     * @param str 字符串
     * @return 判断结果
     */
    public static Boolean isUniqueChar(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            //查看后面是否有当前字符
            if (str.indexOf(c, i + 1) != -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 通过正则寻找
     *
     * @param str   字符串
     * @param regex 正则表达式
     * @return 结果list
     */
    public static List<StringResult> findByRegex(String str, String regex) {
        ArrayList<StringResult> stringResults = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        int index = 0;
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            StringResult build = StringResult.builder()
                    .matchIndex(index)
                    .startIndex(start)
                    .endIndex(end)
                    .stringValue(str.substring(start, end))
                    .build();
            stringResults.add(build);
        }
        return stringResults;
    }

    /**
     * 通过正则寻找
     *
     * @param str     字符串
     * @param regexes 正则表达式
     * @return 结果list
     */
    public static List<StringResult> findByRegex(String str, String... regexes) {
        ArrayList<StringResult> stringResults = new ArrayList<>();
        ArrayList<Pattern> patterns = new ArrayList<>();
        for (String regex : regexes) {
            patterns.add(Pattern.compile(regex));
        }
        int index = 0;
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(str);
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                StringResult build = StringResult.builder()
                        .matchIndex(index)
                        .startIndex(start)
                        .endIndex(end)
                        .stringValue(str.substring(start, end))
                        .build();
                stringResults.add(build);
            }
        }
        return stringResults;
    }

    /**
     * 查找单词
     *
     * @param str  字符串
     * @param word 单词
     * @return 结果list
     */
    public static List<StringResult> findWord(String str, String word) {
        return findByRegex(str, "\\b" + word + "\\b");
    }

    /**
     * 查找单词
     *
     * @param str  字符串
     * @param word 单词
     * @return 结果list
     */
    public static List<StringResult> findWord(String str, String... word) {
        String[] strings = new String[word.length];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = "\\b" + word[i] + "\\b";
        }
        return findByRegex(str, strings);
    }

    /**
     * 查找存在
     *
     * @param str       字符串
     * @param strToFind 要找的字符串
     * @return 结果list
     */
    public static List<StringResult> findExists(String str, String strToFind) {
        return findByRegex(str, ".*" + strToFind + ".*");
    }

    /**
     * 查找存在
     *
     * @param str       字符串
     * @param strToFind 要找的字符串
     * @return 结果list
     */
    public static List<StringResult> findExists(String str, String... strToFind) {
        String[] strings = new String[strToFind.length];
        for (int i = 0; i < strToFind.length; i++) {
            strings[i] = ".*" + strToFind[i] + ".*";
        }
        return findByRegex(str, strings);
    }

    public static void main(String[] args) {
        System.out.println(findByRegex("Apple,love,love", "lo"));
        System.out.println(findWord("apple,banana,orange", "apple"));
    }

    /**
     * 子字符串在母字符串中出现的次数
     * 统计数字出现的次数的时候可以只设置一个int[10]来计数
     *
     * @param mom 母字符串
     * @param son 子字符串
     */
    public static Integer stringTimes(String mom, String son) {
        int times = 0;
        //存在子字符串
        while (mom.contains(son)) {
            times++;
            //将前面全部截掉
            mom = mom.substring(mom.indexOf(son) + son.length());
        }
        return times;
    }

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

    /**
     * 字符串删除空格
     *
     * @param string 原字符串
     * @return 删除空格后的字符串
     */
    public String stringSpaceDelete(String string) {
        return string.replace(" ", "");
    }


}
