package jill.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 视频类型
 *
 * @author jill
 */
@Getter
@AllArgsConstructor
public enum VideoType {

//AVI、mov、rmvb、rm、FLV、mp4、3GP、wmv、MPEG、ram、swf、

    AVI("avi"),
    MOV("mov"),
    MKV("mkv"),
    RMVB("rmvb"),
    RM("rm"),
    FLV("flv"),
    MP4("mp4"),
    ASF("asf"),
    TGP("3gp"),
    WMV("wmv"),
    MPEG("mpeg"),
    RAM("ram"),
    SWF("swf"),
    F4V("f4v");

    private final String code;

    public static VideoType getEnum(String code) {
        for (VideoType instance : values()) {
            if (Objects.equals(instance.getCode(), code)) {
                return instance;
            }
        }
        return null;
        // throw new IllegalArgumentException("unexpected CmsImageType value: " + code);
    }

}