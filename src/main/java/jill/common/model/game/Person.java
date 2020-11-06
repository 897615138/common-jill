package jill.common.model.game;

import java.util.Scanner;

/**
 * 用户类
 *
 * @author JIll Wang
 * @date 2020-07-03 21:11
 **/
public class Person {
    final String name;
    final String character;

    public Person() {
        System.out.println("请输入您的游戏名");
        Scanner sc = new Scanner(System.in);
        this.name = sc.nextLine();
        System.out.println("请选择您的角色：1.张三 2.李四 3.王五");
        int choice = sc.nextInt();
        if (choice == 1) {
            this.character = "张三";
        } else {
            if (choice == 2) {
                this.character = "李四";
            } else {
                this.character = "王五";
            }
        }
        System.out.println("欢迎选择" + character + "的玩家" + name);
    }

    public int showFist() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请出拳：1.布 2.剪刀 3.石头");
        int choice = sc.nextInt();
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
        return choice;
    }
}
