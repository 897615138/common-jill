package jill.common.util;

import cn.hutool.core.util.CharsetUtil;
import jill.common.util.word.CodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class CodeUtilProtoTest {
    private static final String TEST = "test";

    @Test
    public void uuid() {
        log.info(CodeUtil.genUuid());
        Assert.assertNotNull(CodeUtil.genUuid());
    }

    @Test
    public void getMd5() {
//        log.info(CodeUtil.getMd5(TEST));
        Assert.assertNotNull(CodeUtil.getMd5(TEST));
    }

    @Test
    public void charSet() {
        log.info(String.valueOf(CharsetUtil.charset("GBK")));
        log.info(String.valueOf(CharsetUtil.parse("GBK")));
        Assert.assertNotNull(CharsetUtil.charset("GBK"));
    }
}
