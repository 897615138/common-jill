package jill.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * @author jill
 */
public class LogTest {
    static final Log log = LogFactory.getLog(LogTest.class);
    @Test
    public  void Log() {
        log.info("Start process...");
        log.info("Process end.");
    }
}
