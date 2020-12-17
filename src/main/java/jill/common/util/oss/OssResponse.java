package jill.common.util.oss;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

/**
 * @author Jill W
 * @date 2020/12/08
 */
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class OssResponse implements Cloneable {
    private InputStream inputStream;
    private String requestId;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        log.info("clone oss response");
        return super.clone();
    }
}
