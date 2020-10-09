package jill.common.util;

import jill.common.consts.CodeConstants;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.UUID;

import static jill.common.consts.LogConstants.log;


/**
 * @author jill
 */
public class CodeUtil {
    /**
     * 获取UUID
     *
     * @return uuid
     */
    public static String genUuid() {
        String uuid = UUID.randomUUID().toString().replaceAll(CodeConstants.DASH, CodeConstants.NULL);
        return uuid.toLowerCase();
    }
    public static String getMd5(String str) {
        try {
            /*
             * <li>{@code MD5}</li>
             * <li>{@code SHA-1}</li>
             * <li>{@code SHA-256}</li>
             */
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            log.error("gen md5 fail");
            throw new IllegalArgumentException("gen md5 fail");
        }
    }

}
