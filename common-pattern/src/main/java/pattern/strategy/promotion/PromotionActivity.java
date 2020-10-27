package pattern.strategy.promotion;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 优惠活动
 *
 * @author jill
 */
@AllArgsConstructor
@NoArgsConstructor
public class PromotionActivity {
    /**
     * 优惠活动的策略
     */
    private IPromotionStrategy iPromotionStrategy;

    public void execute() {
        iPromotionStrategy.doPromotion();
    }
}
