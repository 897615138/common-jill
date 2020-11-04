package pattern.decorator.passport.upgrade;


import pattern.decorator.passport.old.ISignInService;
import pattern.decorator.passport.old.ResultMsg;

/**
 * @author jill
 */
public class SignInForThirdServiceImpl implements ISignInForThirdService {

    private final ISignInService signInService;

    public SignInForThirdServiceImpl(ISignInService signInService) {
        this.signInService = signInService;
    }

    @Override
    public ResultMsg regist(String username, String password) {
        return signInService.regist(username, password);
    }

    @Override
    public ResultMsg login(String username, String password) {
        return signInService.login(username, password);
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
