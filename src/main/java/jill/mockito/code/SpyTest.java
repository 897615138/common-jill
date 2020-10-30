package jill.mockito.code;

import java.util.LinkedList;

import static jill.common.consts.LogConstants.log;
import static org.mockito.Mockito.*;

/**
 * @author JillW
 * @date 2020/09/30
 */
public class SpyTest {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        LinkedList<String> spy  = spy(list);

//optionally, you can stub out some methods:
// 你可以为某些函数打桩
        when(spy.size()).thenReturn(100);

//using the spy calls *real* methods
// 通过spy对象调用真实对象的函数
        spy.add("one");
        spy.add("two");

//prints "one" - the first element of a list
// 输出第一个元素
        log.info(spy.get(0));

//size() method was stubbed - 100 is printed
// 因为size()函数被打桩了,因此这里返回的是100
        log.info(String.valueOf(spy.size()));

//optionally, you can verify
// 交互验证
        verify(spy).add("one");
        verify(spy).add("two");
    }
}
