/**
 * Copyright (C), 2012-2019, 杭州端点网络科技有限公司
 */
package jill.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文件大类
 *
 * @author jill
 */
@Getter
@AllArgsConstructor
public enum FileType {
    /**
     * code 类型编码 desc 类型描述
     */
    FOLDER(1, "文件夹"),
    IMG(2, "图片"),
    GIF(3, "GIF"),
    VOICE(4, "音频"),
    VIDEO(5, "视频"),
    TEXT(6, "文本"),
    OTHER(7, "其他");

    private final Integer code;
    private final String desc;

    public static FileType getEnum(int code) {
        for (FileType instance : values()) {
            if (instance.getCode() == code) {
                return instance;
            }
        }
        return null;
//        throw new IllegalArgumentException("unexpected FileType value: " + code);
    }

}