package pattern.decorator.passport;


import pattern.decorator.passport.old.SignServiceImpl;
import pattern.decorator.passport.upgrade.ISignInForThirdService;
import pattern.decorator.passport.upgrade.SignInForThirdServiceImpl;

/**
 * @author jill on 2019/3/17.
 */
public class DecoratorTest {

    public static void main(String[] args) {

        //满足一个is-a
        ISignInForThirdService signInForThirdService = new SignInForThirdServiceImpl(new SignServiceImpl());
        signInForThirdService.loginForQq("steadfast");

    }


}
