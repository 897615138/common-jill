package jill.common.model.vote;

/**
 * 具体状态类-重复投票
 *
 * @author JIll Wang
 * @date 2020-06-06 11:50
 **/
public class RepeatVoteState extends VoteState {

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
//重复投票，暂时不做处理
        System.out.println("请不要重复投票");
    }

}
