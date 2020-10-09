package jill.common.util;

import org.junit.Test;

import static jill.common.consts.LogConstants.log;
/**
 * @author jill
 */

public class LogTest {

    @Test
    public  void log() {
        log.info("Start process...");
        log.info("Process end.");
    }
}
