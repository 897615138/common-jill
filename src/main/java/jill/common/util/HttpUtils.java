package jill.common.util;
//
//import jill.common.consts.CodeConstant;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpDelete;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpPut;
//import org.apache.http.conn.ClientConnectionManager;
//import org.apache.http.conn.scheme.Scheme;
//import org.apache.http.conn.scheme.SchemeRegistry;
//import org.apache.http.conn.ssl.SSLSocketFactory;
//import org.apache.http.entity.ByteArrayEntity;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.security.KeyManagementException;
//import java.security.NoSuchAlgorithmException;
//import java.security.cert.X509Certificate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//
///**
// * @author jill
// */
//public class HttpUtils {
//    private final static String INSTANCE = "TLS";
//
//    private final static String HTTPS = "https";
//
//    private final static Integer PORT = 443;
//
//    public static HttpResponse doGet(String host, String path, String method,
//                                     Map<String, String> headers,
//                                     Map<String, String> query)
//            throws Exception {
//        HttpClient httpClient = wrapClient(host);
//        HttpGet request = new HttpGet(buildUrl(host, path, query));
//        for (Map.Entry<String, String> e : headers.entrySet()) {
//            request.addHeader(e.getKey(), e.getValue());
//        }
//
//        return httpClient.execute(request);
//    }
//
//
//    public static HttpResponse doPost(String host, String path, String method,
//                                      Map<String, String> headers,
//                                      Map<String, String> query,
//                                      Map<String, String> bodes)
//            throws Exception {
//        HttpClient httpClient = wrapClient(host);
//
//        HttpPost request = new HttpPost(buildUrl(host, path, query));
//        for (Map.Entry<String, String> e : headers.entrySet()) {
//            request.addHeader(e.getKey(), e.getValue());
//        }
//
//        if (bodes != null) {
//            List<NameValuePair> nameValuePairList = new ArrayList<>();
//
//            for (String key : bodes.keySet()) {
//                nameValuePairList.add(new BasicNameValuePair(key, bodes.get(key)));
//            }
//            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, CodeConstant.CHAR_SET);
//            formEntity.setContentType(CodeConstant.CONTENT_TYPE);
//            request.setEntity(formEntity);
//        }
//
//        return httpClient.execute(request);
//    }
//
//    public static HttpResponse doPost(String host, String path, String method,
//                                      Map<String, String> headers,
//                                      Map<String, String> query,
//                                      String body)
//            throws Exception {
//        HttpClient httpClient = wrapClient(host);
//
//        HttpPost request = new HttpPost(buildUrl(host, path, query));
//        for (Map.Entry<String, String> e : headers.entrySet()) {
//            request.addHeader(e.getKey(), e.getValue());
//        }
//
//        if (StringUtils.isNotBlank(body)) {
//            request.setEntity(new StringEntity(body, CodeConstant.CHAR_SET));
//        }
//
//        return httpClient.execute(request);
//    }
//
//
//    public static HttpResponse doPost(String host, String path, String method,
//                                      Map<String, String> headers,
//                                      Map<String, String> query,
//                                      byte[] body)
//            throws Exception {
//        HttpClient httpClient = wrapClient(host);
//
//        HttpPost request = new HttpPost(buildUrl(host, path, query));
//        for (Map.Entry<String, String> e : headers.entrySet()) {
//            request.addHeader(e.getKey(), e.getValue());
//        }
//
//        if (body != null) {
//            request.setEntity(new ByteArrayEntity(body));
//        }
//
//        return httpClient.execute(request);
//    }
//
//
//    public static HttpResponse doPut(String host, String path, String method,
//                                     Map<String, String> headers,
//                                     Map<String, String> query,
//                                     String body)
//            throws Exception {
//        HttpClient httpClient = wrapClient(host);
//
//        HttpPut request = new HttpPut(buildUrl(host, path, query));
//        for (Map.Entry<String, String> e : headers.entrySet()) {
//            request.addHeader(e.getKey(), e.getValue());
//        }
//
//        if (StringUtils.isNotBlank(body)) {
//            request.setEntity(new StringEntity(body, CodeConstant.CHAR_SET));
//        }
//
//        return httpClient.execute(request);
//    }
//
//
//    public static HttpResponse doPut(String host, String path, String method,
//                                     Map<String, String> headers,
//                                     Map<String, String> query,
//                                     byte[] body)
//            throws Exception {
//        HttpClient httpClient = wrapClient(host);
//
//        HttpPut request = new HttpPut(buildUrl(host, path, query));
//        for (Map.Entry<String, String> e : headers.entrySet()) {
//            request.addHeader(e.getKey(), e.getValue());
//        }
//
//        if (body != null) {
//            request.setEntity(new ByteArrayEntity(body));
//        }
//
//        return httpClient.execute(request);
//    }
//
//
//    public static HttpResponse doDelete(String host, String path, String method,
//                                        Map<String, String> headers,
//                                        Map<String, String> query)
//            throws Exception {
//        HttpClient httpClient = wrapClient(host);
//
//        HttpDelete request = new HttpDelete(buildUrl(host, path, query));
//        for (Map.Entry<String, String> e : headers.entrySet()) {
//            request.addHeader(e.getKey(), e.getValue());
//        }
//
//        return httpClient.execute(request);
//    }
//
//    private static String buildUrl(String host, String path, Map<String, String> query)
//            throws UnsupportedEncodingException {
//        StringBuilder sbUrl = new StringBuilder();
//        sbUrl.append(host);
//        if (!StringUtils.isBlank(path)) {
//            sbUrl.append(path);
//        }
//        if (null != query) {
//            StringBuilder sbQuery = new StringBuilder();
//            for (Map.Entry<String, String> queryT : query.entrySet()) {
//                if (0 < sbQuery.length()) {
//                    sbQuery.append("&");
//                }
//                if (StringUtils.isBlank(queryT.getKey()) && !StringUtils.isBlank(queryT.getValue())) {
//                    sbQuery.append(queryT.getValue());
//                }
//                if (!StringUtils.isBlank(queryT.getKey())) {
//                    sbQuery.append(queryT.getKey());
//                    if (!StringUtils.isBlank(queryT.getValue())) {
//                        sbQuery.append("=");
//                        sbQuery.append(URLEncoder.encode(queryT.getValue(), CodeConstant.CHAR_SET));
//                    }
//                }
//            }
//            if (0 < sbQuery.length()) {
//                sbUrl.append("?").append(sbQuery);
//            }
//        }
//
//        return sbUrl.toString();
//    }
//
//    private static HttpClient wrapClient(String host) {
//        HttpClient httpClient = new DefaultHttpClient();
//        if (host.startsWith(CodeConstant.WEB_PREFIX_TWO)) {
//            sslClient(httpClient);
//        }
//
//        return httpClient;
//    }
//
//    private static void sslClient(HttpClient httpClient) {
//        try {
//            SSLContext ctx = SSLContext.getInstance(INSTANCE);
//            X509TrustManager tm = new X509TrustManager() {
//                @Override
//                public X509Certificate[] getAcceptedIssuers() {
//                    return null;
//                }
//
//                @Override
//                public void checkClientTrusted(X509Certificate[] xcs, String str) {
//
//                }
//
//                @Override
//                public void checkServerTrusted(X509Certificate[] xcs, String str) {
//
//                }
//            };
//            ctx.init(null, new TrustManager[]{tm}, null);
//            SSLSocketFactory ssf = new SSLSocketFactory(ctx);
//            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//            ClientConnectionManager ccm = httpClient.getConnectionManager();
//            SchemeRegistry registry = ccm.getSchemeRegistry();
//            registry.register(new Scheme(HTTPS, PORT, ssf));
//        } catch (KeyManagementException | NoSuchAlgorithmException ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//}
