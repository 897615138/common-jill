package jill.common.model.game;

/**
 * 计算机类
 * @author  JIll Wang
 * @date  2020-07-03 21:11
 **/
public class Computer {
    /**
     * 电脑随机出拳
     *
     * @return 返回
     */
    public int computerShowFist() {
        int computer = (int) (Math.random() * 3 + 1);
        String choice2;
        if ((computer != 1)) {
            if (2 == computer) {
                choice2 = "剪刀";
            } else {
                choice2 = "石头";
            }
        } else {
            choice2 = "布";
        }
        System.out.printf("电脑的选择：%s%n", choice2);
        return computer;
    }
}
