package jill.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;


/**
 * @author jill
 */
@Slf4j
public class StreamUtil {
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
