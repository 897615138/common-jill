package pattern.adapter.loginadapter.v2.adapters;


import pattern.adapter.loginadapter.ResultMsg;


/**
 * @author jill
 */
public class LoginForSinaAdapter implements LoginAdapter {
    @Override
    public boolean support(Object adapter) {
        return adapter instanceof LoginForSinaAdapter;
    }

    @Override
    public ResultMsg login(String id, Object adapter) {
        return new ResultMsg(200, "Sina", null);
    }
}
