package jill.common.util;

import org.junit.Assert;
import org.junit.Test;

import static jill.common.consts.LogConstants.log;

public class IpUtilTest {
    @Test
    public void getIP() {
        Assert.assertNotNull(IpUtil.getHostAddress());
        log.info(IpUtil.getHostAddress());
    }
}
