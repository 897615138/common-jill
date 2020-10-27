package pattern.strategy.pay;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pattern.strategy.pay.payport.PayStrategy;
import pattern.strategy.pay.payport.AbstractPayment;

/**
 * @author jill
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String uid;
    private String orderId;
    private double amount;

    public MsgResult pay() {
        return pay(PayStrategy.DEFAULT_PAY);
    }

    public MsgResult pay(String payKey) {
        AbstractPayment abstractPayment = PayStrategy.get(payKey);
        System.out.println("欢迎使用" + abstractPayment.getName());
        System.out.println("本次交易金额为：" + amount + "，开始扣款...");
        return abstractPayment.pay(uid, amount);
    }
}
