package pattern.adapter.loginadapter;

/**
 * @author jill
 */
public class SignInService {

    /**
     * 注册方法
     *
     * @param username 用户名
     * @param password 密码
     * @return 结果
     */
    public ResultMsg regist(String username, String password) {
        if (username.equals(password)) {
            return new ResultMsg(200, "注册成功", new Member());
        } else {
            return new ResultMsg(400, "注册失败", null);
        }
    }


    /**
     * 登录的方法
     *
     * @param username 用户名
     * @param password 密码
     * @return 结果
     */
    public ResultMsg login(String username, String password) {
        if (username.equals(password)) {
            return new ResultMsg(200, "登录成功", new Member());
        } else {
            return new ResultMsg(400, "登录失败", null);
        }
    }

}
