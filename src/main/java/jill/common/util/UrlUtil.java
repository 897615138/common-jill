package jill.common.util;

import cn.hutool.core.codec.Base64;
import com.aliyun.openservices.log.http.client.HttpMethod;
import org.apache.http.client.utils.URIBuilder;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Jill W
 * @date 2020/11/19
 */
public class UrlUtil {
    private static final String MAC_NAME = "HmacSHA1";
    private static final String ENCODING = StandardCharsets.UTF_8.name();
    private static final String ADDRESS = "https://saf.cn-shanghai.aliyuncs.com";

    /**
     * 记得修改请求头 Content-Type: application/x-www-form-urlencoded
     *
     * @param u               请求URL
     * @param accessKeySecret accessKeySecret
     * @return 返回
     * @throws MalformedURLException 异常
     */
    public static URL getRiskRequest(URL u, String accessKeySecret) throws Exception {
        URL url = new URL(ADDRESS);
        URI signature = new URIBuilder(u.toString()).addParameter("Signature", signature(u.getQuery(), accessKeySecret)).build();
        System.out.println(url);
        return url;
    }

    /**
     * * i.对于字符A-Z、a-z、0-9以及字符"-"、"_"、"."、"~"不编码；
     * * ii. 对于其它字符编码成"%XY"的格式，其中XY是字符对应ASCII码的16进制表示。比如英文的双引号（"）对应的编码就是%22
     * * iii.对于扩展的UTF-8字符，编码成"%XY%ZA…"的格式；
     * * iv. 需要说明的是英文空格（ ）要被编码是%20，而不是加号（+）。
     *
     * @param value string
     * @return 返回
     * @throws UnsupportedEncodingException 不支持的编码异常
     */
    public static String percentEncode(String value) throws UnsupportedEncodingException {
        return value != null ? URLEncoder.encode(value, ENCODING).replace("+", "%20").replace("*", "%2A").replace("%7E", "~") : null;
    }


    /**
     * 请求转换出签名
     * 记得增加了 Signature 参数和修改请求头 Content-Type: application/x-www-form-urlencoded
     *
     * @param str             请求
     * @param accessKeySecret accessKeySecret
     * @return 签名
     * @throws Exception 异常
     */
    public static String signature(String str, String accessKeySecret) throws Exception {
        /*StringToSign=
                HTTPMethod + “&” +
                percentEncode(“/”) + ”&” +
                percentEncode(CannibalizedQueryString*/
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
        byte[] bytes = macSha1Encrypt(toSign, accessKeySecret + "&");
        String base64Str = Base64.encode(bytes);
        System.out.println(base64Str);
        return base64Str;
    }

    private static String getUtf8Encoder(String param) throws UnsupportedEncodingException {
        return URLEncoder.encode(param, "UTF-8")
                .replaceAll("\\+", "%20")
                .replaceAll("\\*", "%2A")
                .replaceAll("%7E", "~");
    }

    /**
     * 使用 HMAC-SHA1 签名方法对对encryptText进行签名
     *
     * @param encryptText 被签名的字符串
     * @param encryptKey  密钥 AccessKeySecret 加上一个 & 字符 (ASCII:38)，使用的哈希算法是 SHA1
     * @return 返回
     * @throws Exception 异常
     */
    public static byte[] macSha1Encrypt(String encryptText, String encryptKey) throws Exception {
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

    public static void main(String[] args) {
        try {
            System.out.println(new URIBuilder("http://example.com?email=john.doe@email.com").addParameter("name", "john").build().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param u               url
     * @param accessKeySecret accessKeySecret
     * @return 返回
     * @throws IOException 异常
     */
    public static HttpURLConnection getAliHttpURLConnection(URL u, String accessKeySecret) throws Exception {
        u = getRiskRequest(u, accessKeySecret);
        HttpURLConnection connection = (HttpURLConnection) u.openConnection();
        // 设置请求方式为post
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        return connection;
    }

    /**
     * 接口调用  POST
     */
    public static void httpURLConnectionPOST() {
        try {
            //传递参数
            String Parma = "?cardType={}&cardID={}";
            URL url = new URL(ADDRESS);
            // 将url 以 open方法返回的urlConnection  链接强转为HttpURLConnection链接  (标识一个url所引用的远程对象链接)
            // 此时connection只是为一个链接对象,待链接中
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置链接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
            connection.setDoOutput(true);
            // 设置链接输入流为true
            connection.setDoInput(true);
            // 设置请求方式为post
            connection.setRequestMethod("POST");
            // post请求缓存设为false
            connection.setUseCaches(false);
            // 设置该HttpURLConnection实例是否自动执行重定向
            connection.setInstanceFollowRedirects(true);
            // 设置请求头里面的各个属性 (如下为设置内容的类型,设置为通过urlEncoded编码过的from参数)
            // application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据
            // ;charset=utf-8 必需要，否则妙兜那边会出现乱码【★★★★★】
            //addRequestProperty添加相同的key不会覆盖，若是相同，内容会以{name1,name2}
            connection.addRequestProperty("from", "ssh");  //来源哪一个系统
            //setRequestProperty添加相同的key会覆盖value信息
            //setRequestProperty方法，若是key存在，则覆盖；不存在，直接添加。
            //addRequestProperty方法，无论key存在不存在，直接添加。
            connection.setRequestProperty("user", "user");  //访问申请用户
            InetAddress address = InetAddress.getLocalHost();
            String ip = address.getHostAddress();//得到本机IP
            connection.setRequestProperty("ip", ip);  //请求来源IP
            connection.setRequestProperty("entry", "123456");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            // 创建链接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法以前进行)
            connection.connect();
            // 建立输入输出流,用于往链接里面输出携带的参数,(输出内容为?后面的内容)
            DataOutputStream dataset = new DataOutputStream(connection.getOutputStream());
            // 格式 param = aaa=111&bbb=222&ccc=333&ddd=444
            String param = "username=Kansan&password=0000";
            System.out.println("传递参数：" + param);
            // 将参数输出到链接
            dataset.writeBytes(param);
            // 输出完成后刷新并关闭流
            dataset.flush();
            dataset.close(); // 重要且易忽略步骤 (关闭流,切记!)
            //System.out.println(connection.getResponseCode());
            // 链接发起请求,处理服务器响应  (从链接获取到输入流并包装为bufferedReader)
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), ENCODING));
            String line;
            StringBuilder sb = new StringBuilder(); // 用来存储响应数据

            // 循环读取流,若不到结尾处
            while ((line = bf.readLine()) != null) {
                //sb.append(bf.readLine());
                sb.append(line).append(System.getProperty("line.separator"));
            }
            bf.close();    // 重要且易忽略步骤 (关闭流,切记!)
            connection.disconnect(); // 销毁链接
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
