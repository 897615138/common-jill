package jill.common.model.util;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author Jill W
 * @date 2021/01/05
 */
public class ProtoTestTest {

    @Test
    public void writeObj() throws Exception {
        ProtoTest.SearchRequest request = ProtoTest.SearchRequest.newBuilder().setQuery("setQuery").build();
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/terminus/Desktop/test");
        Assert.assertNotNull(fileOutputStream);
        request.writeTo(fileOutputStream);
    }

    @Test
    public void readObj() throws Exception {
        ProtoTest.SearchRequest request = ProtoTest.SearchRequest.parseFrom(new FileInputStream("/Users/terminus/Desktop/test"));
        Assert.assertNotNull(request);
        System.out.printf("setQuery=%s%n", request.getQuery());
    }
}