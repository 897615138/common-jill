package pattern.strategy.promotion;

import java.util.HashMap;
import java.util.Map;

/**
 * 促销策略工厂
 *
 * @author jill
 */
class PromotionStrategyFactory {
    private static final Map<String, IPromotionStrategy> PROMOTION_STRATEGY_MAP = new HashMap<>();
    private static final IPromotionStrategy NON_PROMOTION = new EmptyStrategy();

    static {
        PROMOTION_STRATEGY_MAP.put(PromotionKey.COUPON, new CouponStrategy());
        PROMOTION_STRATEGY_MAP.put(PromotionKey.CASHBACK, new CashbackStrategy());
        PROMOTION_STRATEGY_MAP.put(PromotionKey.GROUP_BUY, new GroupBuyStrategy());
    }

    private PromotionStrategyFactory() {
    }

    static IPromotionStrategy getPromotionStrategy(String promotionKey) {
        IPromotionStrategy iPromotionStrategy = PROMOTION_STRATEGY_MAP.get(promotionKey);
        return iPromotionStrategy == null ? NON_PROMOTION : iPromotionStrategy;
    }

    private interface PromotionKey {
        String COUPON = "COUPON";
        String CASHBACK = "CASHBACK";
        String GROUP_BUY = "GROUP_BUY";
    }


}
