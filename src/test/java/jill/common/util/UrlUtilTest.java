package jill.common.util;

import cn.hutool.core.codec.Base64;
import com.aliyun.openservices.log.http.client.HttpMethod;
import org.junit.Assert;
import org.junit.Test;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author Jill W
 * @date 2020/11/19
 */
public class UrlUtilTest {
    private static final String MAC_NAME = "HmacSHA1";
    private static final String ENCODING = "UTF-8";

    /**
     * 使用 HMAC-SHA1 签名方法对对encryptText进行签名
     *
     * @param encryptText 被签名的字符串
     * @param encryptKey  密钥
     * @return 返回
     * @throws Exception 异常
     */
    public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception {
        byte[] data = encryptKey.getBytes(ENCODING);
//根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
//生成一个指定 Mac 算法的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME);
//用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        byte[] text = encryptText.getBytes(ENCODING);
//完成 Mac 操作
        return mac.doFinal(text);
    }

    @Test
    public void test() throws Exception {
/*StringToSign=
                HTTPMethod + “&” +
                percentEncode(“/”) + ”&” +
percentEncode(CannibalizedQueryString*/
        String str = "AccessKeyId=testId&AccountName=<a%b'>&Action=SingleSendMail&AddressType=1&Format=XML&HtmlBody=4&RegionId=cn-hangzhou&ReplyToAddress=true&SignatureMethod=HMAC" +
                "-SHA1&SignatureNonce=c1b2c332-4cfb-4a0f-b8cc-ebe622aa0a5c&SignatureVersion=1.0&Subject=3&TagName=2&Timestamp=2016-10-20T06:27:56Z&ToAddress=1@test.com&Version=2015-11-23";
        StringBuilder percentStr = new StringBuilder();
        String[] strings = str.split("&");
        for (String s : strings) {
            String[] str1 = s.split("=");
            if (str1.length == 1) {
                percentStr.append(getUtf8Encoder(str1[0])).append("=").append(getUtf8Encoder("")).append("&");
            } else {
                percentStr.append(getUtf8Encoder(str1[0])).append("=").append(getUtf8Encoder(str1[1])).append("&");
            }
        }
        percentStr = new StringBuilder(percentStr.substring(0, percentStr.lastIndexOf("&")));
        String percent = URLEncoder.encode("/", "UTF-8");
        percentStr = new StringBuilder(getUtf8Encoder(percentStr.toString()));
        String toSign = HttpMethod.POST + "&" + percent + "&" + percentStr;
        System.out.println("--------------" + toSign);
        /*AccessKeyId%3Dtestid&AccountName%3D%253Ca%2525b%2527%253E&Action%3DSingleSendMail&AddressType%3D1&Format%3DXML&HtmlBody%3D4&RegionId%3Dcn-hangzhou&ReplyToAddress%3Dtrue&SignatureMethod%3DHMAC-SHA1&SignatureNonce%3Dc1b2c332-4cfb-4a0f-b8cc-ebe622aa0a5c&SignatureVersion%3D1.0&Subject%3D3&TagName%3D2&Timestamp%3D2016-10-20T06%253A27%253A56Z&ToAddress%3D1%2540test.com&Version%3D2015-11-23*/
        byte[] bytes = HmacSHA1Encrypt(toSign, "testsecret&");
        String base64Str = Base64.encode(bytes);
        Assert.assertNotNull(base64Str);
        System.out.println(base64Str);//llJfXJjBW3OacrVgxxsITgYaYm0=
    }

    private String getUtf8Encoder(String param) throws UnsupportedEncodingException {
        return URLEncoder.encode(param, "UTF-8")
                .replaceAll("\\+", "%20")
                .replaceAll("\\*", "%2A")
                .replaceAll("%7E", "~");
    }
}
