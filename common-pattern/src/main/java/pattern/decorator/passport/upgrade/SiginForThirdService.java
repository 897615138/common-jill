package pattern.decorator.passport.upgrade;


import pattern.decorator.passport.old.ISigninService;
import pattern.decorator.passport.old.ResultMsg;

/**
 * @author jill on 2019/3/17.
 */
public class SiginForThirdService implements ISiginForThirdService {

    private final ISigninService signinService;

    public SiginForThirdService(ISigninService signinService) {
        this.signinService = signinService;
    }

    @Override
    public ResultMsg regist(String username, String password) {
        return signinService.regist(username, password);
    }

    @Override
    public ResultMsg login(String username, String password) {
        return signinService.login(username, password);
    }

    @Override
    public ResultMsg loginForQq(String id) {
        return null;
    }

    @Override
    public ResultMsg loginForWechat(String id) {
        return null;
    }

    @Override
    public ResultMsg loginForToken(String token) {
        return null;
    }

    @Override
    public ResultMsg loginForTelephone(String telephone, String code) {
        return null;
    }

    @Override
    public ResultMsg loginForRegist(String username, String passport) {
        return null;
    }
}
