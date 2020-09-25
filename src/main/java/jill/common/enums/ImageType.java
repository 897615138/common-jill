/**
 * Copyright (C), 2012-2019, 杭州端点网络科技有限公司
 */
package jill.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 图片类型枚举
 *
 * @author jill
 */
@Getter
@AllArgsConstructor
public enum ImageType {

    //{"xls", "doc", "ppt", "movie", "avi", "rgb", "png", "jpe", "jpg", "jpeg", "gif", "bmp", "wav", "ra", "rm", "mp3", "mpga", "pdf"};

    PNG("png"),
    // JPE("jpe"),
    JPG("jpg"),
    JPEG("jpeg")
    // ,
    // BMP("bmp"),
    // TIF("tif"),
    // TGA("tga"),
    // EPS("eps")
    ;

    private final String code;

    public static ImageType getEnum(String code) {
        for (ImageType instance : values()) {
            if (Objects.equals(instance.getCode(), code)) {
                return instance;
            }
        }
        return null;
//        throw new IllegalArgumentException("unexpected ImageType value: " + code);
    }
}