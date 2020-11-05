package jill.common.util;

import jill.common.model.StringResult;

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
     * TODO 给很多正则 只要匹配即可
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
     * 查找单词
     *
     * @param str  字符串
     * @param word 单词
     * @return 结果list
     * TODO 给很多字符串只要匹配即可
     */
    public static List<StringResult> findWords(String str, String word) {
        return findByRegex(str, "\\b" + word + "\\b");
    }

    /**
     * 查找存在
     *
     * @param str       字符串
     * @param strToFind 要找的字符串
     * @return 结果list
     * TODO 给很多字符串只要匹配即可
     */
    public static List<StringResult> findExists(String str, String strToFind) {
        return findByRegex(str, ".*" + strToFind + ".*");
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

    public static void main(String[] args) {
        System.out.println(findByRegex("Apple,love,love", "lo"));
        System.out.println(findWords("apple,banana,orange", "apple"));
    }

    /**
     * 子字符串在母字符串中出现的次数
     * 统计数字出现的次数的时候可以只设置一个int[10]来计数
     *
     * @param mom 母字符串
     * @param son 子字符串
     * TODO 给很多子字符串或者很多母字符串
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
}
