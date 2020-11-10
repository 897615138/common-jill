package jill.common.model.game.gate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author JIll Wang
 * @date 2020-07-08 08:21
 **/

public class Square {
    /**
     * 开始标志
     */
    public final static int SQUARE_START = -2;
    /**
     * 结束标志
     */
    public final static int SQUARE_END = -3;
    /**
     * 路径
     */
    public final static int SQUARE_PATH = 0;
    /**
     * 障碍标志
     */
    public final static int SQUARE_BLOCK = -1;
    /**
     * x坐标
     */
    public final int x;
    /**
     * y坐标
     */
    public final int y;
    /**
     * >0就是传送门
     */
    public final int type;

    Square(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public static void solve(Scanner in) {
        System.out.println("输入两个整数并且以空格分开");
        String[] dimension = in.nextLine().split(" ");
        int width = Integer.parseInt(dimension[0]);
        System.out.println(width);
        int height = Integer.parseInt(dimension[1]);
        Square[][] map = new Square[width][height];
        Square start = null;
        Square end = null;
        Map<Integer, WrapGate> wrapGate = new HashMap<>(width * height);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] squares = line.split(" ");
            int y = 0;
            for (int x = 0; x < width; x++) {
                map[x][y] = new Square(x, y, Integer.parseInt(squares[x]));
                switch (map[x][y].type) {
                    case Square.SQUARE_START:
                        start = map[x][y];
                        break;
                    case Square.SQUARE_END:
                        end = map[x][y];
                        break;
                    default:
                        if (map[x][y].isWrapGate()) {
                            int gateNumber = map[x][y].type;
                            WrapGate wrapGate1 = wrapGate.get(gateNumber);
                            if (wrapGate1 == null) {
                                wrapGate1 = new WrapGate();
                                wrapGate.put(gateNumber, wrapGate1);
                            }
                            wrapGate1.buildWrapGate(map[x][y]);

                        }
                }
                y++;
            }
            if (start == null || end == null) {
                throw new RuntimeException("No Start or End Found");
            }
        }
//        for (String i : test
//        ) {
//            System.out.println(String.valueOf(i));
//        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Square)) {
            return false;
        }
        Square square = (Square) o;
        return x == square.x &&
                y == square.y &&
                type == square.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public boolean isWrapGate() {
        return type > 0;

    }


}