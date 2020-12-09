package jill.mockito.code;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

import static org.mockito.Mockito.*;

/**
 * 冗余
 *
 * @author JillW
 * @date 2020/09/30
 */
@SuppressWarnings("ALL")
@Slf4j
public class RedundancyTest {
    public static void main(String[] args) {
        //创建mock对象
        LinkedList mockedList = mock(LinkedList.class);
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
