package pattern.decorator.passport.upgrade;


import pattern.decorator.passport.old.ISignInService;
import pattern.decorator.passport.old.ResultMsg;

/**
 * @author jill on 2019/3/17.
 */
public interface ISignInForThirdService extends ISignInService {

    /**
     * QQ登录
     *
     * @param id id
     * @return return
     */
    ResultMsg loginForQq(String id);

    /**
     * 微信登录
     *
     * @param id id
     * @return return
     */
    ResultMsg loginForWechat(String id);

    /**
     * 记住登录状态后自动登录
     *
     * @param token token
     * @return return
     */
    ResultMsg loginForToken(String token);

    /**
     * 手机号登录
     *
     * @param telephone t
     * @param code      c
     * @return r
     */
    ResultMsg loginForTelephone(String telephone, String code);

    /**
     * 注册后自动登录
     *
     * @param username u
     * @param passport p
     * @return r
     */
    ResultMsg loginForRegist(String username, String passport);
}
