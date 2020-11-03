package pattern.decorator.passport.old;

/**
 * @author jill
 */
public interface ISignInService {
    ResultMsg regist(String username, String password);


    /**
     * 登录的方法
     *
     * @param username u
     * @param password p
     * @return re
     */
    ResultMsg login(String username, String password);
}
