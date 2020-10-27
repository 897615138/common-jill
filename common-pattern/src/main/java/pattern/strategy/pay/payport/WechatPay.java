package pattern.strategy.pay.payport;

/**
 * @author jill
 */
public class WechatPay extends AbstractPayment {

    @Override
    public String getName() {
        return "微信支付";
    }

    @Override
    protected double queryBalance(String uid) {
        return 256;
    }

}
