package jill.common.model.warehouse;


import jill.common.util.BeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 仓库
 * 假设某企业自产、自存、自销，需要将工厂生产的各类产品不定时的运到仓库，与此同时，需要将仓库中的货物运往超市和商场中进行销售，请编写一个程序模拟此过程（主要是存取这个过程）。
 * <p>
 * 仓库的存量是固定的，可以假设为一个常量，比如10。
 * 仓库满的时候，不能再向仓库中存货。
 * 仓库空的时候，不能卖出货物。
 * 存货和取货是同时进行的，不要出现先存满再取完货再存满再取完的效果或者存一个取一个再存再取这样的效果。
 *
 * @author JIll Wang
 * @date 2020-07-14 17:54
 **/
public class Warehouse {
    /**
     * 仓库数量上限
     */
    public static final Integer CAPACITY = 10;
    public static final Scanner SCANNER = new Scanner(System.in);
    /**
     * 仓库商品目录
     */
    protected static final List<Commodity> GOOD_LIST = new ArrayList<>();
    /**
     * 仓库现有数量
     */
    public static Integer getCapacity;

    //初始化
    static {
        for (int i = 0; i < 10; i++) {
            Commodity commodity = new Commodity();
            commodity.setName(String.valueOf(i));
            commodity.setNumber(0);
            GOOD_LIST.add(commodity);
        }
        //System.out.println(goodList);
        getCapacity = 0;
    }

    /**
     * 商品录入
     */
    public static void goodEntry(Commodity commodity) {
        //判断商品在不在商品目录里
        if (GOOD_LIST.contains(commodity)) {
            //重写了equals 名字相同则相同
            System.out.println("仓库中有该商品目录");
        } else {
            //商品不存在添加商品目录
            Commodity commodity1 = new Commodity();
            commodity1.setName(commodity.getName());
            commodity1.setNumber(0);
            GOOD_LIST.add(commodity1);
            System.out.println("添加新的商品类型");
        }
        //判断仓库是否容得下加入的商品
        if (getCapacity + commodity.getNumber() > CAPACITY) {
            System.out.println("超出仓库承受范围");
        } else {
            //添加商品
            int i = GOOD_LIST.get(GOOD_LIST.indexOf(commodity)).getNumber() + commodity.getNumber();
            GOOD_LIST.get(GOOD_LIST.indexOf(commodity)).setNumber(i);
            System.out.println(GOOD_LIST.get(GOOD_LIST.indexOf(commodity)));
            getCapacity += commodity.getNumber();
        }

    }

    /**
     * 商品提出
     */
    public static void goodGet(Commodity commodity) {

        //判断商品在不在商品目录里
        if (GOOD_LIST.contains(commodity)) {
            //重写了equals 名字相同则相同
            System.out.println("仓库中有该商品");
            //判断仓库是否有足够的该商品
            int i = GOOD_LIST.get(GOOD_LIST.indexOf(commodity)).getNumber();
            if (i < commodity.getNumber()) {
                System.out.println("仓库库存不足");
            } else {
                //提走商品
                GOOD_LIST.get(GOOD_LIST.indexOf(commodity)).setNumber(i - commodity.getNumber());
                System.out.println(GOOD_LIST.get(GOOD_LIST.indexOf(commodity)));
                getCapacity -= commodity.getNumber();
            }
        } else {
            //商品不存在添加商品目录
            GOOD_LIST.add(commodity);
            System.out.println("仓库内没有该商品");
        }


    }

    /**
     * 面板
     */
    public static void pattern() {
        System.out.println("请问是存货还是提货？退出请输入0");
        String choice = SCANNER.nextLine();
        switch (choice) {
            case "存货":
                Commodity bean1 = BeanUtil.manualInsertBean(Commodity.class);
                goodEntry(bean1);
                break;
            case "取货":
                Commodity bean2 = BeanUtil.manualInsertBean(Commodity.class);
                goodGet(bean2);
                break;
            case "0":
                break;
            default:
                System.out.println("请输入正确的选择");
        }


    }

    public static void main(String[] args) {
        pattern();
    }

}