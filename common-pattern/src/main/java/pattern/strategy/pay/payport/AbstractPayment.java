package pattern.strategy.pay.payport;


import pattern.strategy.pay.MsgResult;

/**
 * 支付渠道
 *
 * @author jill
 */
public abstract class AbstractPayment {

    /**
     * 支付类型
     */
    public abstract String getName();

    /**
     * 查询余额
     *
     * @param uid uid
     * @return 余额
     */
    protected abstract double queryBalance(String uid);

    /**
     * 扣款支付
     *
     * @param uid    uid
     * @param amount 价格
     * @return 支付结果
     */
    public MsgResult pay(String uid, double amount) {
        if (queryBalance(uid) < amount) {
            return new MsgResult(500, "支付失败", "余额不足");
        }
        return new MsgResult(200, "支付成功", "支付金额：" + amount);
    }


}
