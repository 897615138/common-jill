package jill.common.model.vote;

/**
 * 具体状态类-黑名单
 *
 * @author JIll Wang
 * @date 2020-06-06 11:51
 **/
public class BlackVoteState extends VoteState {

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
//记录黑名单中，禁止登录系统
        System.out.println("进入黑名单，将禁止登录和使用本系统");
    }

}