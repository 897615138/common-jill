/**
 * Copyright (C), 2012-2019, 杭州端点网络科技有限公司
 */
package jill.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 文本类型
 *
 * @author jill
 */
@Getter
@AllArgsConstructor
public enum TextType {
    /**
     *
     */
    TXT("txt"),
    CSV("csv"),
    WORD("doc"),
    WORDS("docx"),
    EXCEL("xls"),
    EXCELS("xlsx");

    private String code;

    public static TextType getEnum(String code) {
        for (TextType instance : values()) {
            if (Objects.equals(instance.getCode(), code)) {
                return instance;
            }
        }
        return null;
    }
}