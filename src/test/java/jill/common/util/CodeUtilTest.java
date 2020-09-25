package jill.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class CodeUtilTest {
    static final Log log = LogFactory.getLog(CodeUtilTest.class);
    static final String TEST="test";

    @Test
    public void uuid() {
        log.info(CodeUtil.genUuid());
    }

    @Test
    public void getMd5(){log.info(CodeUtil.getMd5(TEST));}
}
