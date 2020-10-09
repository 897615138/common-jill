package jill.mockito.code;

import java.util.LinkedList;

import static org.mockito.Mockito.*;
import static jill.common.consts.LogConstants.log;
/**
 * @author JillW
 * @date 2020/09/30
 */
public class SpyTest2 {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        LinkedList<String> spy = spy(list);


//You have to use doReturn() for stubbing
// 你需要使用doReturn()来打桩
        doReturn("foo").when(spy).get(0);
        //Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
// 不可能 : 因为当调用spy.get(0)时会调用真实对象的get(0)函数,此时会发生IndexOutOfBoundsException异常，因为真实List对象是空的
        log.info(spy.get(0));
        when(spy.get(0)).thenReturn("foo");
    }
}
