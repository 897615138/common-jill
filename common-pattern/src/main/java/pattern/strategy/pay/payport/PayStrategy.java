package pattern.strategy.pay.payport;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付策略管理
 *
 * @author jill
 */
public class PayStrategy {
    private static final String ALI_PAY = "AliPay";
    public static final String DEFAULT_PAY = ALI_PAY;
    private static final String JD_PAY = "JdPay";
    private static final String UNION_PAY = "UnionPay";
    private static final String WECHAT_PAY = "WechatPay";
    private static final Map<String, AbstractPayment> PAY_STRATEGY = new HashMap<>();

    static {
        PAY_STRATEGY.put(ALI_PAY, new AliPay());
        PAY_STRATEGY.put(WECHAT_PAY, new WechatPay());
        PAY_STRATEGY.put(UNION_PAY, new UnionPay());
        PAY_STRATEGY.put(JD_PAY, new JDPay());
    }

    public static AbstractPayment get(String payKey) {
        if (!PAY_STRATEGY.containsKey(payKey)) {
            return PAY_STRATEGY.get(DEFAULT_PAY);
        }
        return PAY_STRATEGY.get(payKey);
    }
}
