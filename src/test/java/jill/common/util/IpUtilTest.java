package jill.common.util;

import org.junit.Test;

import static jill.common.consts.LogConstants.log;

public class IpUtilTest {
    @Test
    public void getIP(){
        log.info(IpUtil.getHostAddress());
    }
}
