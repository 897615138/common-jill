package pattern.strategy.pay.payport;

/**
 * @author jill
 */
public class UnionPay extends AbstractPayment {

    @Override
    public String getName() {
        return "银联支付";
    }

    @Override
    protected double queryBalance(String uid) {
        return 120;
    }

}
