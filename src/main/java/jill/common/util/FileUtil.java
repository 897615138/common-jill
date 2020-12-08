package jill.common.util;

import cn.hutool.core.util.StrUtil;
import jill.common.constant.LogConstant;

import java.io.File;

import static cn.hutool.core.util.CharUtil.DOT;

/**
 * @author jill
 */
public class FileUtil {
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
        if (StrUtil.isNotEmpty(fileName)) {
            if (StrUtil.contains(fileName, DOT) && !StrUtil.endWith(fileName,
                    DOT)) {
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
                LogConstant.log.warn("file make dir fail");
                if (!file.exists()) {
                    throw new RuntimeException("create directory fail");
                }
            }
        }
        return file;
    }
}
