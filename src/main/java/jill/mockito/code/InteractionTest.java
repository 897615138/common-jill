package jill.mockito.code;

import java.util.LinkedList;

import static jill.common.constant.LogConstant.log;
import static org.mockito.Mockito.*;

/**
 * @author JillW
 * @date 2020/09/30
 */
@SuppressWarnings("ALL")
public class InteractionTest {
    public static void main(String[] args) {
        //创建mock对象
        LinkedList mockOne = mock(LinkedList.class);
        LinkedList mockTwo = mock(LinkedList.class);
        LinkedList mockThree = mock(LinkedList.class);
        //using mocks - only mockOne is interacted
        // 使用Mock对象
        mockOne.add("one");
        mockTwo.add("two");
        //ordinary verification
        // 普通验证
        verify(mockOne).add("one");

        //verify that method was never called on a mock
        // 验证某个交互是否从未被执行
        verify(mockOne, never()).add("two");

        //verify that other mocks were not interacted
        // 验证mock对象没有交互过
        verifyNoInteractions(mockTwo, mockThree);
        log.info("ok");

    }
}
