package jill.common.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

/**
 * @author jill
 */
public class StreamUtil {
    static final Log log = LogFactory.getLog(StreamUtil.class);
    /**
     * 输入流
     */
    private InputStream inputStream;
    /**
     * 输入流转输出流
     *
     * @param outputStream 输出流
     */
    public void convertOutStream(OutputStream outputStream) throws IOException {
        if (Objects.isNull(outputStream)) {
            log.error("outputStream is null");
            throw new IllegalArgumentException();
        }
        if (Objects.nonNull(inputStream)) {
            IOUtils.copy(inputStream, outputStream);
        }
    }
}
