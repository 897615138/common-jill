package jill.common.model.vote;

/**
 * 客户端类
 *
 * @author JIll Wang
 * @date 2020-06-06 11:53
 **/
public class Client {

    public static void main(String[] args) {

        VoteManager vm = new VoteManager();
        for (int i = 0; i < 9; i++) {
            vm.vote("u1", "A");
        }
    }

}