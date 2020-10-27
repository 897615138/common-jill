package pattern.decorator.passport;


import pattern.decorator.passport.old.SigninService;
import pattern.decorator.passport.upgrade.ISiginForThirdService;
import pattern.decorator.passport.upgrade.SiginForThirdService;

/**
 * Created by jill on 2019/3/17.
 */
public class DecoratorTest {

    public static void main(String[] args) {

        //满足一个is-a
        ISiginForThirdService siginForThirdService = new SiginForThirdService(new SigninService());
        siginForThirdService.loginForQQ("sdfasfdasfsf");

    }


}
