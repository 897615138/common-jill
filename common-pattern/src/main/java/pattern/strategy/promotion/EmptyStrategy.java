package pattern.strategy.promotion;
/**
 * 无优惠
 * Created by jill
 */
public class EmptyStrategy implements PromotionStrategy {
    public void doPromotion() {
        System.out.println("无促销活动");
    }
}
