package jill.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * GIF类型枚举
 *
 * @author Jill
 */
@Getter
@AllArgsConstructor
public enum GifType {
    /*

     */
    GIF("gif");
    private final String code;

    public static GifType getEnum(String code) {
        for (GifType instance : values())
            if (Objects.equals(instance.getCode(), code)) {
                return instance;
            }
        return null;
        // throw new IllegalArgumentException("unexpected CmsImageType value: " + code);
    }
}