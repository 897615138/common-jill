package jill.common.model.warehouse;


import jill.common.util.BeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * �ֿ�
 * ����ĳ��ҵ�Բ����Դ桢��������Ҫ�����������ĸ����Ʒ����ʱ���˵��ֿ⣬���ͬʱ����Ҫ���ֿ��еĻ����������к��̳��н������ۣ����дһ������ģ��˹��̣���Ҫ�Ǵ�ȡ������̣���
 * <p>
 * �ֿ�Ĵ����ǹ̶��ģ����Լ���Ϊһ������������10��
 * �ֿ�����ʱ�򣬲�������ֿ��д����
 * �ֿ�յ�ʱ�򣬲����������
 * �����ȡ����ͬʱ���еģ���Ҫ�����ȴ�����ȡ����ٴ�����ȡ���Ч�����ߴ�һ��ȡһ���ٴ���ȡ������Ч����
 *
 * @author JIll Wang
 * @date 2020-07-14 17:54
 **/
public class Warehouse {
    /**
     * �ֿ���������
     */
    public static final Integer CAPACITY = 10;
    public static final Scanner SCANNER = new Scanner(System.in);
    /**
     * �ֿ���ƷĿ¼
     */
    protected static final List<Commodity> GOOD_LIST = new ArrayList<>();
    /**
     * �ֿ���������
     */
    public static Integer getCapacity;

    //��ʼ��
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
     * ��Ʒ¼��
     */
    public static void goodEntry(Commodity commodity) {
        //�ж���Ʒ�ڲ�����ƷĿ¼��
        if (GOOD_LIST.contains(commodity)) {
            //��д��equals ������ͬ����ͬ
            System.out.println("�ֿ����и���ƷĿ¼");
        } else {
            //��Ʒ�����������ƷĿ¼
            Commodity commodity1 = new Commodity();
            commodity1.setName(commodity.getName());
            commodity1.setNumber(0);
            GOOD_LIST.add(commodity1);
            System.out.println("����µ���Ʒ����");
        }
        //�жϲֿ��Ƿ��ݵ��¼������Ʒ
        if (getCapacity + commodity.getNumber() > CAPACITY) {
            System.out.println("�����ֿ���ܷ�Χ");
        } else {
            //�����Ʒ
            int i = GOOD_LIST.get(GOOD_LIST.indexOf(commodity)).getNumber() + commodity.getNumber();
            GOOD_LIST.get(GOOD_LIST.indexOf(commodity)).setNumber(i);
            System.out.println(GOOD_LIST.get(GOOD_LIST.indexOf(commodity)));
            getCapacity += commodity.getNumber();
        }

    }

    /**
     * ��Ʒ���
     */
    public static void goodGet(Commodity commodity) {

        //�ж���Ʒ�ڲ�����ƷĿ¼��
        if (GOOD_LIST.contains(commodity)) {
            //��д��equals ������ͬ����ͬ
            System.out.println("�ֿ����и���Ʒ");
            //�жϲֿ��Ƿ����㹻�ĸ���Ʒ
            int i = GOOD_LIST.get(GOOD_LIST.indexOf(commodity)).getNumber();
            if (i < commodity.getNumber()) {
                System.out.println("�ֿ��治��");
            } else {
                //������Ʒ
                GOOD_LIST.get(GOOD_LIST.indexOf(commodity)).setNumber(i - commodity.getNumber());
                System.out.println(GOOD_LIST.get(GOOD_LIST.indexOf(commodity)));
                getCapacity -= commodity.getNumber();
            }
        } else {
            //��Ʒ�����������ƷĿ¼
            GOOD_LIST.add(commodity);
            System.out.println("�ֿ���û�и���Ʒ");
        }


    }

    /**
     * ���
     */
    public static void pattern() {
        System.out.println("�����Ǵ������������˳�������0");
        String choice = SCANNER.nextLine();
        switch (choice) {
            case "���":
                Commodity bean1 = BeanUtil.manualInsertBean(Commodity.class);
                goodEntry(bean1);
                break;
            case "ȡ��":
                Commodity bean2 = BeanUtil.manualInsertBean(Commodity.class);
                goodGet(bean2);
                break;
            case "0":
                break;
            default:
                System.out.println("��������ȷ��ѡ��");
        }


    }

    public static void main(String[] args) {
        pattern();
    }

}