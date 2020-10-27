package pattern.adapter.loginadapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jill
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultMsg {
    /**
     * 结果码
     */
    private int code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 数据
     */
    private Object data;
}
