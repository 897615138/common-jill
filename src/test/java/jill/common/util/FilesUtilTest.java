package jill.common.util;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;

/**
 * @author Jill W
 * @date 2020/12/16
 */
public class FilesUtilTest extends TestCase {

    public void testDeleteFolder() {
    }

    public void testGetFileExt() {
    }

    public void testGetFileOrCreate() {
    }

    public void testMain() {
    }

    public void testDeleteMuchDependencies() {
    }

    public void testCompareVersion() {
    }

    public void testDeleteFile() {
    }
@Test
    public void testGetResource() {
    File resource = FilesUtil.getResource("/mybatis-generator/generatorConfig.xml");
    System.out.println(resource);
}
}