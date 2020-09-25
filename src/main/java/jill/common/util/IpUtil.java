package jill.common.util;

import java.net.InetAddress;

/**
 * @author jill
 */
public class IpUtil {
    /**
     * 获取本机IP
     *
     * @return 本机id
     */
    public static String getHostAddress() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            return address.getHostAddress();
        } catch (Exception e) {
            return "127.0.0.1";
        }
    }
}
