package jill.common.model.vote;

/**
 * 具体状态类-正常投票
 *
 * @author JIll Wang
 * @date 2020-06-06 11:49
 **/
public class NormalVoteState extends VoteState {

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
//正常投票，记录到投票记录中
        voteManager.getMapVote().put(user, voteItem);
        System.out.println("恭喜投票成功");
    }

}