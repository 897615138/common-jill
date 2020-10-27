package pattern.strategy.pay;

/**
 * 支付完成以后的状态
 *
 * @author jill
 */
public class MsgResult {
    private final int code;
    private final Object data;
    private final String msg;

    public MsgResult(int code, String msg, Object data) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return ("支付状态：[" + code + "]," + msg + ",交易详情：" + data);
    }
}
