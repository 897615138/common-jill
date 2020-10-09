package jill.mockito.code;

import java.util.LinkedList;
import static jill.common.consts.LogConstants.log;
import static org.mockito.Mockito.*;
/**
 * @author JillW
 * @date 2020/09/30
 */
public class ArgumentMatcher<T> {
    public static void main(String[] args) {
        //创建mock对象
        LinkedList<String> mockedList = mock(LinkedList.class);
        //stubbing using built-in anyInt() argument matcher
        // 使用内置的anyInt()参数匹配器
        when(mockedList.get(anyInt())).thenReturn("element");

        //following prints "element"
        log.info(mockedList.get(999));

        //you can also verify using an argument matcher
        // 你也可以验证参数匹配器
        verify(mockedList).get(anyInt());
    }
}
