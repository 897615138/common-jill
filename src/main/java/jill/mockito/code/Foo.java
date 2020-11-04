package jill.mockito.code;

import jill.mockito.model.TestModel;

import java.util.LinkedList;

import static jill.common.constant.LogConstant.log;
import static org.mockito.Mockito.*;

/**
 * @author JillW
 * @date 2020/09/30
 */
public class Foo {
    public static void main(String[] args) {
        //创建mock对象
        LinkedList mockedList = mock(LinkedList.class);
        log.info("ok");
        //Creates mock with different default answer & name
        //用不同的默认结果和名字去创建`mock`

        Foo mock1 = mock(Foo.class, withSettings()
                .defaultAnswer(RETURNS_SMART_NULLS)
                .name("cool mock1"));

        //Creates mock with different default answer, descriptive name and extra interfaces
        ////用不同的默认结果和描述的名称以及额外的接口去创建`mock`
        Foo mock2 = mock(Foo.class, withSettings()
                .defaultAnswer(RETURNS_SMART_NULLS)
                .name("cool mock2")
                .extraInterfaces(TestModel.class));


    }
}
