package pattern.adapter.loginadapter.v2.adapters;


import pattern.adapter.loginadapter.ResultMsg;

/**
 * @author jill
 */
public class LoginForTokenAdapter implements LoginAdapter {
    @Override
    public boolean support(Object adapter) {
        return adapter instanceof LoginForTokenAdapter;
    }

    @Override
    public ResultMsg login(String id, Object adapter) {
        return new ResultMsg(200, "token", null);
    }
}
