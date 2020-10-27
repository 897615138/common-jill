package pattern.adapter.loginadapter.v2.adapters;

import pattern.adapter.loginadapter.ResultMsg;


/**
 * @author jill
 */
public class RegistForQQAdapter implements RegistAdapter {
    @Override
    public boolean support(Object adapter) {
        return false;
    }

    @Override
    public ResultMsg regist(String id, Object adapter) {
        return new ResultMsg(200, "qq", null);
    }
}
