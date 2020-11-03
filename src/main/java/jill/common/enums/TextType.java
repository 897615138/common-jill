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

    private final String code;

    public static TextType getEnum(String code) {
        for (TextType instance : values())
            if (Objects.equals(instance.getCode(), code)) {
                return instance;
            }
        return null;
    }
}