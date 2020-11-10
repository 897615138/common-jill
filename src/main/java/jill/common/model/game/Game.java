package jill.common.model.game;

import jill.common.model.game.finger.Computer;

import java.util.Scanner;

/**
 * 游戏类
 *
 * @author JIll Wang
 * @date 2020-07-03 21:11
 **/


public class Game {
    /**
     * 游戏次数
     */
    int times;
    /**
     * 获胜次数
     */
    int win;
    /**
     * 平局次数
     */
    int draw;
    /**
     * 失败次数
     */
    int lose;
    /**
     * 是否进行游戏
     */
    boolean flag1;
    /**
     * 是否进行猜拳
     */
    boolean flag2;


    /**
     * 进行游戏标志
     */
    public void setFlag1(boolean flag1) {
        this.flag1 = flag1;
    }

    /**
     * 进行猜拳标志
     */
    public void setFlag2(boolean flag2) {
        this.flag2 = flag2;
    }

    /**
     * 判断输赢 更新记录
     *
     * @param choice   选择
     * @param computer 电脑
     * @param game     游戏
     */
    public void judge(int choice, int computer, Game game) {
        if (choice == computer) {
            System.out.println("本局结果：平局");
            game.draw++;
        } else {
            if (choice - 1 == computer || choice + 2 == computer) {
                System.out.println("本局结果：胜出");
                game.win++;
            } else {
                System.out.println("本局结果：失败");
                game.lose++;
            }
        }
        game.times++;
    }

    /**
     * 判断最终结果并显示
     *
     * @param choice 选择
     * @param person 人
     * @param game   游戏
     * @param sc     输入流
     */
    public void judge(String choice, Person person, Game game, Scanner sc) {
        if ("n".equals(choice)) {
            System.out.println("---------------------");
            System.out.println(person.character + '(' + person.name + ')' + "  VS  Computer");
            System.out.println("对战次数：" + game.times);
            System.out.println("战绩：赢 " + game.win + "  平 " + game.draw + " 输 " + game.lose);
            if (win > lose) {
                System.out.println("恭喜获胜");
            } else {
                System.out.println("再接再厉");
                game.setFlag1(false);
                game.setFlag2(false);
            }
            sc.close();
        }
    }

    /**
     * 游戏流程
     */
    public void start() {
        System.out.println("+++++++++划拳游戏++++++++");
        Scanner sc = new Scanner(System.in);
        Person person = new Person();
        Computer computer = new Computer();
        System.out.println("要开始吗？y/n");
        String choice = sc.next();
        if ("y".equals(choice)) {
            Game game = new Game();
            game.setFlag1(true);
            while (game.flag1) {
                game.setFlag2(true);

                while (game.flag2) {
                    int p = person.showFist();
                    int c = computer.computerShowFist();
                    game.judge(p, c, game);
                    System.out.println("是否进行下一轮？y/n");
                    choice = sc.next();
                    game.judge(choice, person, game, sc);
                }
            }
        }
    }
}
