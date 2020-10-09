package jill.mockito.code;

import java.util.LinkedList;

import static jill.common.consts.LogConstants.log;
import static org.mockito.Mockito.*;

/**
 * 冗余
 * @author JillW
 * @date 2020/09/30
 */
public class RedundancyTest {
    public static void main(String[] args) {
        //创建mock对象
        LinkedList<String> mockedList = mock(LinkedList.class);
        //using mocks
        mockedList.add("one");
        mockedList.add("two");

        verify(mockedList).add("one");

//following verification will fail
// 下面的验证将会失败
        verifyNoMoreInteractions(mockedList);
        log.info("ok");

    }
}
