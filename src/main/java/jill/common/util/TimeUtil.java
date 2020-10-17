package jill.common.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.BooleanUtil;
import jill.common.model.TimeModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JillW
 * @date 2020/10/10
 */
public class TimeUtil {
    private static boolean compareTimeContain(TimeModel time1,TimeModel time2){
        boolean and1 = BooleanUtil.and(time1.getStartAt().getTime() <= time2.getStartAt().getTime(),
                time2.getStartAt().getTime() <= time1.getEndAt().getTime());
//        System.out.println("B的左端在A的闭区间内"+and1);
        boolean and2 = BooleanUtil.and(time1.getStartAt().getTime() <= time2.getEndAt().getTime(),
                time2.getEndAt().getTime() <= time1.getEndAt().getTime());
//        System.out.println("B的右端在A的闭区间内"+and2);
        boolean or = BooleanUtil.or(and1, and2);
//        System.out.println("B只要有一端在A的闭区间内"+or);

        boolean and3 = BooleanUtil.and(time2.getStartAt().getTime() < time1.getStartAt().getTime(),
                time1.getEndAt().getTime() < time2.getEndAt().getTime());
//        System.out.println("A在B内"+and3);

        boolean or1 = BooleanUtil.or(and1, and2, and3);
//         System.out.println("有重复部分"+or1);
        return or1;
    }
    public static Boolean compareTimeIn(Date time1,TimeModel time2){
        return time2.getStartAt().getTime()<= time1.getTime()  &&
                time1.getTime()<= time2.getEndAt().getTime();
    }
    public static boolean after(Date time1, Date time2) {
        return time1.getTime()> time2.getTime() ;
    }

    public static void main(String[] args) {
        Date parse1 = DateUtil.parse("1999-07-18");
        Date parse2 = DateUtil.parse("1999-07-20");
        Date parse3 = DateUtil.parse("1999-07-22");
        Date parse4 = DateUtil.parse("1999-07-24");
       // System.out.println(parse1.before(parse2));

        TimeModel time1 = TimeModel.builder().startAt(parse1).endAt(parse2).build();
        TimeModel time2 = TimeModel.builder().startAt(parse2).endAt(parse3).build();
        TimeModel time3 = TimeModel.builder().startAt(parse3).endAt(parse4).build();
        TimeModel time4 = TimeModel.builder().startAt(parse1).endAt(parse3).build();
        TimeModel time5 = TimeModel.builder().startAt(parse2).endAt(parse4).build();
        TimeModel time6 = TimeModel.builder().startAt(parse1).endAt(parse4).build();
        compareTimeContain(time6,time2);

        ArrayList<TimeModel> timeModels = CollUtil.newArrayList(time2, time3,time4);
        List<TimeModel> collect = timeModels.stream().filter((timeModel -> compareTimeContain(timeModel, time1))).collect(Collectors.toList());
        System.out.println(collect);

        System.out.println(compareTimeIn(parse2,time2));
        System.out.println(compareTimeIn(parse2,time3));
        System.out.println(compareTimeIn(parse2,time4));

//        System.out.println(compareTimeIn(parse4,time4));


        List<TimeModel> collect1 = timeModels.stream().filter((timeModel -> compareTimeIn(parse2, timeModel))).collect(Collectors.toList());
        System.out.println(collect1);
        System.out.println(after(parse2,parse1));

        System.out.println(parse1.toString());


    }

}
