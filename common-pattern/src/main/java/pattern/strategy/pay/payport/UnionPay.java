package pattern.strategy.pay.payport;

/**
 * Created by jill.
 */
public class UnionPay extends Payment {

    public String getName() {
        return "银联支付";
    }

    protected double queryBalance(String uid) {
        return 120;
    }

}
