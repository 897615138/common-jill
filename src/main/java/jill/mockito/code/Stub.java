package jill.mockito.code;

// 静态导入会使代码更简洁

import jill.common.consts.LogConstants;

import java.util.LinkedList;

import static jill.common.consts.LogConstants.log;
import static org.mockito.Mockito.*;

/**
 * @author JillW
 * @date 2020/09/30
 * 测试桩 Stub
 */
public class Stub {
    public static void main(String[] args) {
        //You can mock concrete classes, not only interfaces
        // 你可以mock具体的类型,不仅只是接口
        LinkedList<String> mockedList = mock(LinkedList.class);

        //stubbing
        // 测试桩
        when(mockedList.get(0)).thenReturn("ok");
        when(mockedList.get(1)).thenReturn("wrong");

        //following prints "first"
        // 输出“first”
        log.info(mockedList.get(0));
        //following throws runtime exception
        // 抛出异常 thenThrow
        log.info(mockedList.get(1));
        //following prints "null" because get(999) was not stubbed
        // 因为get(999) 没有打桩，因此输出null
        log.info(mockedList.get(999));

        //Although it is possible to verify a stubbed invocation, usually it's just redundant
        //If your code cares what get(0) returns then something else breaks (often before even verify() gets executed).
        //If your code doesn't care what get(0) returns then it should not be stubbed. Not convinced? See here.
        // 验证get(0)被调用的次数
        String s = verify(mockedList).get(0);
        log.info(s);
        log.info(LogConstants.CROSS_LINE);
    }
}
