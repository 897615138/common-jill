package pattern.strategy.pay.payport;


/**
 * @author jill
 */
public class JDPay extends AbstractPayment {

    @Override
    public String getName() {
        return "京东白条";
    }

    @Override
    protected double queryBalance(String uid) {
        return 500;
    }
}
