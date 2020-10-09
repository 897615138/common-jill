package jill.common.util;

import org.junit.Test;

import static jill.common.consts.LogConstants.log;
public class FileUtilTest {

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
        log.info(FileUtil.getFileType(TEST_TYPE).getDesc());
    }

    @Test
    public void getOssFileKey(){
        log.info(FileUtil.getOssFileKey(TEST_FILE));
    }

}

