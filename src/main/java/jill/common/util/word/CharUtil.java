package jill.common.util.word;

/**
 * 字符型工具
 *
 * @author Jill W
 * @date 2020/11/05
 */
public class CharUtil {
    /**
     * Character -> Integer
     * 转大写
     * char[] wordArray = word.toCharArray();
     * wordArray[0]-=32;
     * return String.valueOf(wordArray);
     *
     * @param c Character
     * @return Integer
     */
    public static Integer charToInteger(Character c) {
        return c - 48;
    }

    public static void change(char[] element, int aPosition, int bPosition) {
        //中间量
        char temp = element[aPosition];
        element[aPosition] = element[bPosition];
        element[bPosition] = temp;
    }

    /**
     * 全排序
     *
     * @param element 字符串元素
     * @param begin   排序的开始元素位置
     * @param end     排序的结束元素位置
     */
    public char[] fullPermutation(char[] element, int begin, int end) {
        //排序完成就输出
        if (begin == end) {
            return element;
        }
        for (int point = begin; point <= end; point++) {
            change(element, begin, point);
            //begin已经确定元素
            fullPermutation(element, begin + 1, end);
            //还原到原来的样子
            change(element, begin, point);
        }
        return element;
    }
}
