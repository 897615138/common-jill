package pattern.adapter.loginadapter.v2.adapters;


import pattern.adapter.loginadapter.ResultMsg;

/**
 * 在适配器里面，这个接口是可有可无，不要跟模板模式混淆
 * 模板模式一定是抽象类，而这里仅仅只是一个接口
 *
 * @author jill
 */
public interface LoginAdapter {
    /**
     * support
     *
     * @param adapter 适配器
     * @return 是否支持
     */
    boolean support(Object adapter);

    /**
     * 登录
     *
     * @param id      用户id
     * @param adapter 适配器
     * @return 结果
     */
    ResultMsg login(String id, Object adapter);

}
