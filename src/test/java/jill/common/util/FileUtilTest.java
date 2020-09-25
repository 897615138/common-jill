package jill.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class FileUtilTest {
    static final Log log = LogFactory.getLog(FileUtilTest.class);
    static final String TEST_FILE="test.txt";
    static final String TEST_TYPE="jpg";
    @Test
    public void testFile (){
        log.info(FileUtil.getFileName(TEST_FILE));
    }

    @Test
    public void fileExtension(){
        log.info(FileUtil.getFileExt(TEST_FILE));
    }
    @Test
    public void getFileType(){
        log.info(FileUtil.getFileType(TEST_TYPE));
    }

    @Test
    public void getOssFileKey(){
        log.info(FileUtil.getOssFileKey(TEST_FILE));
    }

}

