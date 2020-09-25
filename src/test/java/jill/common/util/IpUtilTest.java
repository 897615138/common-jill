package jill.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class IpUtilTest {
    static final Log log = LogFactory.getLog(IpUtilTest.class);
    @Test
    public void getIP(){
        log.info(IpUtil.getHostAddress());
    }
}
