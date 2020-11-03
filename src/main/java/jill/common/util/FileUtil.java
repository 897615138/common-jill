package jill.common.util;

import cn.hutool.core.date.format.FastDateFormat;
import cn.hutool.core.util.StrUtil;
import jill.common.consts.FileConstants;
import jill.common.enums.*;

import java.util.Date;
import java.util.Objects;

/**
 * @author jill
 */
public class FileUtil {
    /**
     * 获取文件后最后一个.前面的
     *
     * @param fileName 文件名
     * @return 去掉后缀名
     */
    static String getFileName(String fileName) {
        String ext = "";
        if (StrUtil.isNotEmpty(fileName) && fileName.contains(FileConstants.DOT))
            ext = fileName.substring(0, fileName.lastIndexOf(FileConstants.DOT));
        return ext;
    }

    /**
     * 获取文件后缀名不加 .
     *
     * @param fileName 文件名
     * @return 后缀名不加.
     */
    static String getFileExt(String fileName) {
        String ext = "";
        if (StrUtil.isNotEmpty(fileName) && fileName.contains(FileConstants.DOT) &&
                !fileName.endsWith(FileConstants.DOT))
            ext = fileName.substring(fileName.lastIndexOf(FileConstants.DOT) + 1);
        return ext;
    }

    /**
     * 文件后缀获取文件大类
     *
     * @param ext 后缀
     * @return 大类
     */
    static FileType getFileType(String ext) {
        if (StrUtil.isBlank(ext)) return FileType.OTHER;
        ext = ext.toLowerCase();
        if (Objects.nonNull(ImageType.getEnum(ext))) return FileType.IMG;
        else if (Objects.nonNull(GifType.getEnum(ext))) return FileType.GIF;
        else if (Objects.nonNull(VideoType.getEnum(ext))) return FileType.VIDEO;
        else if (Objects.nonNull(jill.common.enums.VoiceType.getEnum(ext))) return FileType.VOICE;
        else if (Objects.nonNull(TextType.getEnum(ext))) return FileType.TEXT;
        return FileType.OTHER;
    }

    /**
     * 文件是够超过最大限制大小
     *
     * @return 最大限制大小
     */
    public static boolean isOverMaxSize(String ext, Long size) {
        FileType fileType = getFileType(ext);
        if (Objects.isNull(fileType)) return false;
        switch (fileType) {
            case GIF:
                if (size > FileConstants.DEFAULT_GIF_SIZE) return true;
                break;
            case IMG:
                if (size > FileConstants.DEFAULT_IMAGE_SIZE) return true;
                break;
            case TEXT:
                if (size > FileConstants.DEFAULT_TEXT_SIZE) return true;
                break;
            case VIDEO:
                if (size > FileConstants.DEFAULT_VIDEO_SIZE) return true;
                break;
            case VOICE:
                if (size > FileConstants.DEFAULT_VOICE_SIZE) return true;
                break;
            default:
                break;
        }
        return false;
    }

    /**
     * 获取OSSFileKey
     *
     * @param fileName 文件名
     * @return OSSFileKey
     */
    static String getOssFileKey(String fileName) {
        if (StrUtil.isBlank(fileName)) throw new IllegalArgumentException("arguments is null");
        //这里不能直接加
        return FileConstants.SYS_PACKAGE + (String) FileConstants.PACKAGE_SEPARATION +
                FastDateFormat.getInstance("yyyy-MM-dd").format(new Date()) +
                FileConstants.PACKAGE_SEPARATION +
                fileName;
    }

    public static void huToolTest() {

    }
}
