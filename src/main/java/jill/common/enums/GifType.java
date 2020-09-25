/**
 * Copyright (C), 2012-2019, 杭州端点网络科技有限公司
 */
package jill.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * GIF类型枚举
 *
 * @author: <a href="mailto:xh168479@alibaba-inc.com">xiehong</a>
 * @date: 2019-09-04 10:28
 */
@Getter
@AllArgsConstructor
public enum GifType {
/*

 */
    GIF("gif");
    private final String code;

    public static GifType getEnum(String code) {
        for (GifType instance : values()) {
            if (Objects.equals(instance.getCode(), code)) {
                return instance;
            }
        }
        return null;
        // throw new IllegalArgumentException("unexpected CmsImageType value: " + code);
    }
}