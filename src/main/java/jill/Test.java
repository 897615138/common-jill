package jill;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * @author Jill W
 * @date 2020/12/17
 */
public class Test {
    public static void main(String[] args) {
        //返回与当前线程关联的语言环境
        Locale locale = LocaleContextHolder.getLocale();
        System.out.println(locale);
    }
}
