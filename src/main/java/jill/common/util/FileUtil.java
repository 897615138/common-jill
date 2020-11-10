package jill.common.util;

import java.io.File;

/**
 * @author jill
 */
public class FileUtil {
    /**
     * 递归删除目录
     *
     * @param srcFolder 文件夹路径
     */
    public void deleteFolder(File srcFolder) {
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
}
