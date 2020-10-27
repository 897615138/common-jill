package pattern.strategy.pay.payport;


/**
 * @author jill
 */
public class AliPay extends AbstractPayment {

    @Override
    public String getName() {
        return "支付宝";
    }

    @Override
    protected double queryBalance(String uid) {
        return 900;
    }

}
