package jill.mockito.code;

import java.util.LinkedList;

import static jill.common.consts.LogConstants.log;
import static org.mockito.Mockito.mock;

/**
 * @author JillW
 * @date 2020/09/30
 */
public class Demo1 {
    public static void main(String[] args) {
        //创建mock对象
        LinkedList<String> mockedList = mock(LinkedList.class);
        log.info("ok");

    }
}
