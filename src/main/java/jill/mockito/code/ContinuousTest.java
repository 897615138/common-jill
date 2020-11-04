package jill.mockito.code;

import java.util.LinkedList;

import static jill.common.constant.LogConstant.log;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author JillW
 * @date 2020/09/30
 */
@SuppressWarnings("ALL")
public class ContinuousTest {
    public static void main(String[] args) {
        //创建mock对象
        LinkedList mock = mock(LinkedList.class);
        when(mock.add("some arg"))
                .thenReturn(true, false, true);
        //when(mock.someMethod("some arg"))
        //   .thenThrow(new RuntimeException())
        //   .thenReturn("foo");
        log.info(String.valueOf(mock.add("some arg")));
        log.info(String.valueOf(mock.add("some arg")));
        log.info(String.valueOf(mock.add("some arg")));
        log.info("ok");

    }
}
