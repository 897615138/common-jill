package jill.common.util;

/**
 * @author JillW
 * @date 2020/09/30
 * 字符串工具
 */
public class StringUtil {
    public static Boolean isUniqueChar(String str){
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            //查看后面是否有当前字符
            if (str.indexOf(c, i + 1) != -1) {
                return false;
            }
        }
        return true;
    }
}
