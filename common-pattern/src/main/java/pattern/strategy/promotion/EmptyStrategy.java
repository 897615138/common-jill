package pattern.strategy.promotion;

/**
 * 无优惠
 *
 * @author jill
 */
public class EmptyStrategy implements IPromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("无促销活动");
    }
}
