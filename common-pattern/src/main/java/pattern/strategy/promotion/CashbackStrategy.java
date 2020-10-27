package pattern.strategy.promotion;

/**
 * 返现活动
 *
 * @author jill
 */
public class CashbackStrategy implements IPromotionStrategy {

    @Override
    public void doPromotion() {
        System.out.println("返现促销,返回的金额转到支付宝账号");
    }
}
