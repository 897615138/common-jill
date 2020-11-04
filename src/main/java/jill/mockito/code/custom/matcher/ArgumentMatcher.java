package jill.mockito.code.custom.matcher;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import jill.mockito.model.TestModel;

import java.util.LinkedList;

import static jill.common.constant.LogConstant.log;
import static org.mockito.Mockito.*;

/**
 * @author JillW
 * @date 2020/09/30
 */
public class ArgumentMatcher {
    public static void main(String[] args) {
        //创建mock对象
        LinkedList mockedList = mock(LinkedList.class);
        //stubbing using built-in anyInt() argument matcher
        // 使用内置的anyInt()参数匹配器
        DateTime dateTime = DateUtil.parseDate("1999-07-18");
        TestMatcher matcher = TestMatcher.builder().name("wj").id(1).birthday(dateTime).build();
        TestModel testModel = TestModel.builder().name("wj").id(1).birthday(dateTime).build();
        boolean matches = matcher.matches(testModel);
        //when(mockedList.contains(argThat(isValid()))).thenReturn("element");
        log.info(String.valueOf(matches));
//        when(mockedList.contains(matcher.matches(build1))

        log.info((String) mockedList.get(999));

        //you can also verify using an argument matcher
        // 你也可以验证参数匹配器
        verify(mockedList).get(anyInt());
    }
}
