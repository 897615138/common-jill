package jill.mockito.code;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

import static org.mockito.Mockito.mock;

/**
 * @author JillW
 * @date 2020/09/30
 */
@Slf4j
public class Demo1 {
    public static void main(String[] args) {
        //创建mock对象
        LinkedList mockedList = mock(LinkedList.class);
        log.info("ok");

    }
}
