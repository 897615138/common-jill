package jill.common.util;

import jill.common.util.web.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
@Slf4j
public class IpUtilTest {
    @Test
    public void getIP() {
        Assert.assertNotNull(IpUtil.getHostAddress());
        log.info(IpUtil.getHostAddress());
    }
}
