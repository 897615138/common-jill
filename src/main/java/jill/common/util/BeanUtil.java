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
     * �ֶ�װ��Bean ��װ�ص�Bean����Ҫ��get set����
     * @param aClass װ��������
     * @param <T> ����
     * @return ����װ�غõ�Bean(��Ҫ�������,����дtoString)
     */
    public static <T> T manualInsertBean(Class<T> aClass) {
        final Scanner scanner = new Scanner(System.in);
        T t = null;
        try {
            t = aClass.getDeclaredConstructor().newInstance();
            //����������� fields
            List<Field> fields = Arrays.asList(aClass.getDeclaredFields());
            //��������� �� ��������
            T finalT = t;
            fields.forEach(el -> {
                String name = el.getName();
                //System.out.println(name);
                Class<?> type = el.getType();
                try {
                    //����method�������Լ���������
                    Method method = aClass.getDeclaredMethod(
                            "set" + name.substring(0, 1).toUpperCase() + name.substring(1), type);
                    System.out.println("������" + name + "ֵ");
                    String line = scanner.nextLine();
                    Object value;
                    //�������null�Ͳ���ֵ
                    if (!("null".equals(line))) {

                        //������ַ����Ͳ���ת��,���ջ��value
                        if (type == String.class) {
                            value = line;
                        } else {
                            //�����Integer����valueOfת��
                            value = Integer.valueOf(line);
                        }
                        //���÷���
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