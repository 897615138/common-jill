package jill.mockito.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Banner平台类型
 *
 * @author JillW
 * @date 2020/10/10
 */
@Getter
@AllArgsConstructor
public enum EmBannerPortalType {

    /**
     * 微信
     */
    WX("wx", "微信"),
    /**
     * 支付宝
     */
    ALIPAY("alipay", "支付宝"),
    /**
     * 网页
     */
    WEB("web", "网页"),
    /**
     * 京东
     */
    JD("jd", "京东");

    private final String code;
    private final String message;

    /**
     * 通过枚举值码查找枚举值。
     *
     * @param code 查找枚举值的枚举值码。
     * @return 枚举值码对应的枚举值。
     * @throws IllegalArgumentException 如果 code 没有对应的 StatusEnum 。
     */
    public static EmBannerPortalType findEnum(String code) {
        for (EmBannerPortalType obj : values())
            if (obj.getCode().equals(code)) {
                return obj;
            }
        throw new IllegalArgumentException("ResultInfo EmBannerPictureType not legal:" + code);
    }
}
