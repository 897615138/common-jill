package pattern.strategy.promotion;

/**
 * 优惠券
 *
 * @author jill
 */
public class CouponStrategy implements IPromotionStrategy {

    @Override
    public void doPromotion() {
        System.out.println("领取优惠券,课程的价格直接减优惠券面值抵扣");
    }
}
