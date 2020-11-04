package jill.mockito.code;

import java.util.LinkedList;

import static jill.common.constant.LogConstant.log;
import static org.mockito.Mockito.*;

/**
 * @author JillW
 * @date 2020/09/30
 */
public class ArgumentMatcher<T> {

        public void add(T e) {
            if (e instanceof String) {
                System.out.println(" I am String");
            } else if (e instanceof Integer) {
                System.out.println("I am Integer");
            }
        }

    public static void main(String[] args) {
        //创建mock对象
        LinkedList mockedList = mock(LinkedList.class);
        //stubbing using built-in anyInt() argument matcher
        // 使用内置的anyInt()参数匹配器
        when(mockedList.get(anyInt())).thenReturn("element");
        //following prints "element"
        log.info((String) mockedList.get(999));

        //you can also verify using an argument matcher
        // 你也可以验证参数匹配器
        verify(mockedList).get(anyInt());
    }
}
