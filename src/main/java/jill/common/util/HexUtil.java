package jill.common.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Jill W
 * @date 2020/11/19
 */
@Slf4j
public class HexUtil {
    public static void main(String[] args) {
        String s = "ab";
        String s1 = cn.hutool.core.util.HexUtil.encodeHexStr(s);
        log.info("ok");
    }
}
