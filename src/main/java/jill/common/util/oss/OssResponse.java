package jill.common.util.oss;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

/**
 * @author Jill W
 * @date 2020/12/08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OssResponse implements Cloneable {
    private InputStream inputStream;
    private String requestId;
}
