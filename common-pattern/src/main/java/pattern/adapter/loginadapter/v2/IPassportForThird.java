package pattern.adapter.loginadapter.v2;


import pattern.adapter.loginadapter.ResultMsg;

/**
 * 只扩展
 *
 * @author jill
 */
public interface IPassportForThird {

    /**
     * QQ登录
     *
     * @param id id
     * @return 结果
     */
    ResultMsg loginForQQ(String id);

    /**
     * 微信登录
     *
     * @param id id
     * @return 结果
     */
    ResultMsg loginForWechat(String id);

    /**
     * 记住登录状态后自动登录
     *
     * @param token token
     * @return 结果
     */
    ResultMsg loginForToken(String token);

    /**
     * 手机号登录
     *
     * @param telephone 手机号
     * @param code      code
     * @return 结果
     */
    ResultMsg loginForTelephone(String telephone, String code);

    /**
     * 注册后自动登录
     *
     * @param username 用户名
     * @param passport 密码
     * @return 结果
     */
    ResultMsg loginForRegist(String username, String passport);
}
