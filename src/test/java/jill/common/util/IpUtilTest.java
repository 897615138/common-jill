package jill.common.util;

import jill.common.util.web.IpUtil;
import org.junit.Assert;
import org.junit.Test;

import static jill.common.constant.LogConstant.log;

public class IpUtilTest {
    @Test
    public void getIP() {
        Assert.assertNotNull(IpUtil.getHostAddress());
        log.info(IpUtil.getHostAddress());
    }
}
