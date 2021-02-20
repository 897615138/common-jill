package jill.common.util;


import cn.hutool.crypto.SecureUtil;

import java.util.HashMap;

/**
 * @author Jill W
 * @date 2021/01/05
 */
public class Md5Util {

//   private static final ArrayList<String> mobileMd5List=new ArrayList<>();
    private static final HashMap<String, String> mobileMd5Map= new HashMap<>();
   static {
       for (long i=10000000000L;i<=19999999999L;i++)
       {
           mobileMd5Map.put(SecureUtil.md5(String.valueOf(i)), String.valueOf(i));
       }
   }

    public static String getMobileMd5(String s) {
        return mobileMd5Map.get(s);
    }
}
