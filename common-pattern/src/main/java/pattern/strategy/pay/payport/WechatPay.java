package pattern.strategy.pay.payport;

/**
 * Created by jill.
 */
public class WechatPay extends Payment {

    public String getName() {
        return "微信支付";
    }

    protected double queryBalance(String uid) {
        return 256;
    }

}
