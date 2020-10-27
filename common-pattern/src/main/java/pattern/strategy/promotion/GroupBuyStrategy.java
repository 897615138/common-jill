package pattern.strategy.promotion;

/**
 * 拼团优惠
 *
 * @author jill
 */
public class GroupBuyStrategy implements IPromotionStrategy {

    @Override
    public void doPromotion() {
        System.out.println("拼团，满20人成团，全团享受团购价");
    }
}
