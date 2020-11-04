package pattern.adapter.loginadapter.v2;

import pattern.adapter.loginadapter.ResultMsg;

/**
 * @author jill
 */
public class PassportTest {

    public static void main(String[] args) {

        IPassportForThird passportForThird = new PassportForThirdAdapter();

        ResultMsg resultMsg = passportForThird.loginForQq("");
        System.out.println(resultMsg);

    }
}
