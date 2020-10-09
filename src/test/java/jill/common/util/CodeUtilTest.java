package jill.common.util;

import cn.hutool.core.util.CharsetUtil;
import org.junit.Test;

import static jill.common.consts.LogConstants.log;

public class CodeUtilTest {
    static final String TEST="test";

    @Test
    public void uuid() {
        log.info(CodeUtil.genUuid());
    }

    @Test
    public void getMd5(){log.info(CodeUtil.getMd5(TEST));}

    @Test
    public void charSet(){
        log.info(String.valueOf(CharsetUtil.charset("GBK")));
        log.info(String.valueOf(CharsetUtil.parse("GBK")));
    }
}
