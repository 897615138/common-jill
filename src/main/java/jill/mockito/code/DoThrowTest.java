package jill.mockito.code;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

/**
 * @author JillW
 * @date 2020/09/30
 */
@Slf4j
public class DoThrowTest {

    public static void main(String[] args) {
        //创建mock对象
        LinkedList mockedList = mock(LinkedList.class);
        doThrow(new RuntimeException()).when(mockedList).clear();
        //following throws RuntimeException:
        //调用这句代码会抛出异常
        mockedList.clear();
        log.info("ok");

    }
}
