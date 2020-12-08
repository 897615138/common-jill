package jill.common.util.oss;

import java.io.InputStream;

/**
 * @author Jill W
 * @date 2020/12/08
 */
public interface OssClient {
    /**
     * oss上传
     *
     * @param inputStream 输入流
     * @param targetObjectKey 目标key
     * @return String
     */

    /**
     * oss下载
     *
     * @param targetObjectKey 目标key
     * @return 返回对象
     */
    OssResponse doDownload(String targetObjectKey);

    /**
     * oss client 链接关闭，对外暴露
     *
     * @param requestId requestId
     */
    void shutDown(String requestId);

    /**
     *
     * @param fileKey fileKey
     * @return url
     */
    String getUrl(String fileKey);
}
