package jill.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author JIll Wang
 * @date 2020-07-14 11:33
 **/
public class BeanUtil {


    /**
     * 手动装载Bean 所装载的Bean必须要有get set方法
     * @param aClass 装载类类型
     * @param <T> 泛型
     * @return 返回装载好的Bean(若要检查内容,需重写toString)
     */
    public static <T> T manualInsertBean(Class<T> aClass) {
        final Scanner scanner = new Scanner(System.in);
        T t = null;
        try {
            t = aClass.getDeclaredConstructor().newInstance();
            //获得所有属性 fields
            List<Field> fields = Arrays.asList(aClass.getDeclaredFields());
            //获得属性名 和 属性类型
            T finalT = t;
            fields.forEach(el -> {
                String name = el.getName();
                //System.out.println(name);
                Class<?> type = el.getType();
                try {
                    //设置method的名称以及参数类型
                    Method method = aClass.getDeclaredMethod(
                            "set" + name.substring(0, 1).toUpperCase() + name.substring(1), type);
                    System.out.println("请输入" + name + "值");
                    String line = scanner.nextLine();
                    Object value;
                    //如果输入null就不赋值
                    if (!("null".equals(line))) {

                        //如果是字符串就不用转换,最终获得value
                        if (type == String.class) {
                            value = line;
                        } else {
                            //如果是Integer就用valueOf转换
                            value = Integer.valueOf(line);
                        }
                        //调用方法
                        method.invoke(finalT, value);
                    }


                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return t;
    }


}