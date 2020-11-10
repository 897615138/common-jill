package jill.common.model.vote;

/**
 * 具体状态类-恶意刷票
 *
 * @author JIll Wang
 * @date 2020-06-06 11:51
 **/
public class SpiteVoteState extends VoteState {

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
// 恶意投票，取消用户的投票资格，并取消投票记录
        String str = voteManager.getMapVote().get(user);
        if (str != null) {
            voteManager.getMapVote().remove(user);
        }
        System.out.println("你有恶意刷屏行为，取消投票资格");
    }

}