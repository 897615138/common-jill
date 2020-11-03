package pattern.decorator.passport;


import pattern.decorator.passport.old.SignService;
import pattern.decorator.passport.upgrade.ISignInForThirdService;
import pattern.decorator.passport.upgrade.SignInForThirdService;

/**
 * @author jill on 2019/3/17.
 */
public class DecoratorTest {

    public static void main(String[] args) {

        //满足一个is-a
        ISignInForThirdService signInForThirdService = new SignInForThirdService(new SignService());
        signInForThirdService.loginForQq("steadfast");

    }


}
