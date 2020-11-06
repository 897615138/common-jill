package jill.common.model.game;

import java.util.Scanner;

/**
 * 一个程序的猜拳游戏
 *
 * @author JIll Wang
 * @date 2020-07-03 20:14
 **/
public class FingerGuessingGameV1 {
    public static final Scanner SC = new Scanner(System.in);
    public static String name = null;
    public static String character = null;
    public static int choice;

    public static void fingerGuessingGame() {
        initGame();
        System.out.println("要开始吗？1/0");
        boolean flag1;
        choice = SC.nextInt();
        if (choice == 1) {
            flag1 = true;
            while (flag1) {
                boolean flag2 = true;
                int times = 0;
                int win = 0;
                int draw = 0;
                int lose = 0;
                while (flag2) {
                    System.out.println("请出拳：1.布 2.剪刀 3.石头");
                    choice = SC.nextInt();
                    String choice2;
                    if (choice == 1) {
                        choice2 = "布";
                    } else {
                        if (choice == 2) {
                            choice2 = "剪刀";
                        } else {
                            choice2 = "石头";
                        }
                    }
                    System.out.println("您的选择：" + choice2);
                    int computer = (int) (Math.random() * 3 + 1);
                    if (computer == 1) {
                        choice2 = "布";
                    } else {
                        if (computer == 2) {
                            choice2 = "剪刀";
                        } else {
                            choice2 = "石头";
                        }
                    }
                    System.out.println("电脑的选择：" + choice2);
                    if (choice == computer) {
                        System.out.println("本局结果：平局");
                        draw++;
                    } else {
                        if (choice - 1 == computer || choice + 2 == computer) {
                            System.out.println("本局结果：胜出");
                            win++;
                        } else {
                            System.out.println("本局结果：失败");
                            lose++;
                        }
                    }
                    times++;
                    System.out.println("是否进行下一轮？1/0");
                    choice = SC.nextInt();
                    if (choice == 0) {
                        System.out.println("---------------------");
                        System.out.println(character + "  VS  Computer");
                        System.out.println("对战次数：" + times);
                        System.out.println("战绩：赢 " + win + "  平 " + draw + " 输 " + lose);
                        if (win > lose) {
                            System.out.println("恭喜获胜");
                        } else {
                            System.out.println("再接再厉");
                            flag2 = false;
                            flag1 = false;
                        }
                    }
                }
            }
        }
    }

    private static void initGame() {
        System.out.println("+++++++++划拳游戏++++++++");
        System.out.println("请输入您的游戏名");
        name = SC.nextLine();
        System.out.println("请选择您的角色：1.张三 2.李四 3.王五");
        choice = SC.nextInt();
        if (choice == 1) {
            character = "张三";
        } else {
            if (choice == 2) {
                character = "李四";
            } else {
                character = "王五";
            }
        }
        System.out.println("欢迎选择" + character + "的玩家" + name);
    }

    public static void main(String[] args) {
        fingerGuessingGame();
    }
}
