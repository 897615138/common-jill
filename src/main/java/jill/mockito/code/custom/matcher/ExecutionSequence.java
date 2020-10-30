package jill.mockito.code.custom.matcher;

import org.mockito.InOrder;

import java.util.LinkedList;

import static jill.common.consts.LogConstants.log;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

/**
 * @author JillW
 * @date 2020/09/30
 */
public class ExecutionSequence {
    public static void main(String[] args) {
        //创建mock对象
        LinkedList<String> singleMock = mock(LinkedList.class);
        //using a single mock

        singleMock.add("was added second");
        singleMock.add("was added first");

        //create an inOrder verifier for a single mock
        // 为该mock对象创建一个inOrder对象
        InOrder inOrder1 = inOrder(singleMock);

        //following will make sure that add is first called with "was added first, then with "was added second"
        // 确保add函数首先执行的是add("was added first"),然后才是add("was added second")
        inOrder1.verify(singleMock).add("was added first");
        inOrder1.verify(singleMock).add("was added second");

        // B. Multiple mocks that must be used in a particular order
        // B .验证多个mock对象的函数执行顺序
        LinkedList<String> firstMock  = mock(LinkedList.class);
        LinkedList<String> secondMock = mock(LinkedList.class);

        //using mocks
        firstMock.add("was called first");
        secondMock.add("was called second");

        //create inOrder object passing any mocks that need to be verified in order
        // 为这两个Mock对象创建inOrder对象
        InOrder inOrder2 = inOrder(firstMock, secondMock);

        //following will make sure that firstMock was called before secondMock
        // 验证它们的执行顺序
        inOrder2.verify(firstMock).add("was called first");
        inOrder2.verify(secondMock).add("was called second");

        // Oh, and A + B can be mixed together at will
        log.info("ok");

    }
}
