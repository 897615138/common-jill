package jill.common.util;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static cn.hutool.core.util.StrUtil.DOT;


/**
 * @author jill
 */
@Slf4j
public class FilesUtil {
    /**
     * 递归删除目录
     *
     * @param srcFolder 文件夹路径
     */
    public static void deleteFolder(File srcFolder) {
        File[] fileArray = srcFolder.listFiles();
        if (fileArray != null) {
            for (File file : fileArray) {
                if (file.isDirectory()) {
                    deleteFolder(file);
                } else {
                    System.out.println(file.getName() + ":" + file.delete());
                }
            }
            System.out.println(srcFolder.getName() + ":" + srcFolder.delete());
        }
    }

    /**
     * 获取无后缀名的文件名
     *
     * @param fileName 文件名
     * @return 去后缀名的文件名
     */
    public static String getFileExt(String fileName) {
        String ext = "";
        //文件名非空
        if (StrUtil.isNotEmpty(fileName)) {
            //文件名有. 并且不是以.结尾
            if (StrUtil.contains(fileName, DOT) && !StrUtil.endWith(fileName, DOT)) {
                ext = fileName.substring(fileName.lastIndexOf(DOT) + 1);
            }
        }
        return ext;
    }

    /**
     * 获得文件 如果不存在就创建文件夹
     *
     * @param pathname 路径名称
     * @return 文档
     */
    public static File getFileOrCreate(String pathname) {
        File file = new File(pathname);
        if (file.exists()) {
            return file;
        } else {
            try {
                file.mkdirs();
            } catch (Exception e) {
                log.warn("file make dir fail");
                if (!file.exists()) {
                    throw new RuntimeException("create directory fail");
                }
            }
        }
        return file;
    }

    public static void main(String[] args) {
        File file = FileUtil.newFile("/Users/terminus/.m2/repository");
        deleteMuchDependencies(file);
    }

    /**
     * 删除指定文件下除了最高版本的依赖文件
     * @param file 文件
     */
    public static void deleteMuchDependencies(File file) {
//        System.out.println(file);
        if (file.isDirectory()) {
            if (Character.isDigit(file.getName().charAt(0))) {
//                System.out.println(file.getName());
                File parentFile = file.getParentFile();
                File[] files = parentFile.listFiles();
                if (files != null) {
                    List<File> sort = Arrays.stream(files).sorted().collect(Collectors.toList());
                    for (int i = 0; i < sort.size() - 1; i++) {
                        File file1 = sort.get(i);
                        String name = file1.getName();
                        System.out.println(name);
                        deleteFile(file);
                    }
                }
            } else {
                File[] files = file.listFiles();
                if (files != null) {
                    for (File file1 : files) {
                        deleteMuchDependencies(file1);
                    }
                }
            }
        }
    }

    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     * 规则是按日期订的例如：2.10.15   项目启动第2年的8月15号
     *
     * @param version1 版本号1
     * @param version2 版本号2
     * @return 大小结果
     */
    public static int compareVersion(String version1, String version2) throws Exception {

        if (version1 == null || version2 == null) {
            throw new Exception("compareVersion error:illegal params.");
        }
        String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用"."；
        for (int i = 0; i < versionArray1.length; i++) { //如果位数只有一位则自动补零（防止出现一个是04，一个是5 直接以长度比较）
            if (versionArray1[i].length() == 1) {
                versionArray1[i] = "0" + versionArray1[i];
            }
        }
        String[] versionArray2 = version2.split("\\.");
        for (int i = 0; i < versionArray2.length; i++) {//如果位数只有一位则自动补零
            if (versionArray2[i].length() == 1) {
                versionArray2[i] = "0" + versionArray2[i];
            }
        }
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
        int diff = 0;
        while (idx < minLength
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符
            ++idx;
        }
//如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

    public static void deleteFile(File file) {
        //判断文件不为null或文件目录存在
        if (file == null || !file.exists()) {
            System.out.println("文件删除失败,请检查文件路径是否正确");
            return;
        }
        //取得这个目录下的所有子文件对象
        File[] files = file.listFiles();
        //遍历该目录下的文件对象
        if (files != null) {
            for (File f : files) {
                //打印文件名
                String name = file.getName();
                System.out.println(name);
                //判断子目录是否存在子目录,如果是文件则删除
                if (f.isDirectory()) {
                    deleteFile(f);
                } else {
                    f.delete();
                }
            }
        }
        //删除空文件夹  for循环已经把上一层节点的目录清空。
        file.delete();
    }
}
