package jill.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @author Jill W
 * @date 2020/11/18
 */
@Getter
@AllArgsConstructor
public enum ComputeTypeEnum {
    /**
     * 等于
     */
    E(0, "等于"),
    /**
     * 小于
     */
    L(1, "小于"),
    /**
     * 大于
     */
    M(2, "大于"),
    LE(3, "小于等于"),
    ME(4, "大于等于"),
    LL(5, "A<X<B"),
    LE_L(6, "A<=X<B"),
    L_LE(7, "A<X<=B"),
    LE_LE(8, "A<=X<=B"),
    LIKE(9, "like"),
    NE(10, "不等于"),
    NOT_NULL(11, "非空");

    private final int code;
    private final String desc;

    public static String getDesc(int code) {
        for (ComputeTypeEnum instance : values()) {
            if (instance.getCode() == code) {
                return instance.getDesc();
            }
        }
        throw new IllegalArgumentException("unexpected riskStrategyEnum code: " + code);
    }

    public static int getCode(String desc) {
        for (ComputeTypeEnum instance : values()) {
            if (Objects.equals(instance.getDesc(), desc)) {
                return instance.code;
            }
        }
        throw new IllegalArgumentException("unexpected riskStrategyEnum desc: " + desc);
    }

}
