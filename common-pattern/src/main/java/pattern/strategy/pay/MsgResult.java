package pattern.strategy.pay;

import lombok.AllArgsConstructor;

/**
 * 支付完成以后的状态
 *
 * @author jill
 */
@AllArgsConstructor
public class MsgResult {
    private final int code;
    private final Object data;
    private final String msg;

    @Override
    public String toString() {
        return ("支付状态：[" + code + "]," + msg + ",交易详情：" + data);
    }
}
