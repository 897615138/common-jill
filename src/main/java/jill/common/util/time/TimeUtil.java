package jill.common.util.time;

import jill.common.model.util.TimeModel;

import java.io.Serializable;
import java.util.Date;

/**
 * @author JillW
 * @date 2020/10/10
 */
public class TimeUtil implements Serializable {
    private static final long serialVersionUID = 5619743170608617827L;

    public static Boolean compareTimeIn(Date time1, TimeModel time2) {
        return time2.getStartAt().getTime() <= time1.getTime() &&
                time1.getTime() <= time2.getEndAt().getTime();
    }

    public static boolean after(Date time1, Date time2) {
        return time1.getTime() > time2.getTime();
    }

//    public static void main(String[] args) {
//        Date parse1 = DateUtil.parse("1999-07-18");
//        Date parse2 = DateUtil.parse("1999-07-20");
//        Date parse3 = DateUtil.parse("1999-07-22");
//        Date parse4 = DateUtil.parse("1999-07-24");
//       // System.out.println(parse1.before(parse2));
//
//        TimeModel time1 = TimeModel.builder().startAt(parse1).endAt(parse2).build();
//        TimeModel time2 = TimeModel.builder().startAt(parse2).endAt(parse3).build();
//        TimeModel time3 = TimeModel.builder().startAt(parse3).endAt(parse4).build();
//        TimeModel time4 = TimeModel.builder().startAt(parse1).endAt(parse3).build();
//        TimeModel time5 = TimeModel.builder().startAt(parse2).endAt(parse4).build();
//        TimeModel time6 = TimeModel.builder().startAt(parse1).endAt(parse4).build();
//        compareTimeContain(time6,time2);
//
//        ArrayList<TimeModel> timeModels = CollUtil.newArrayList(time2, time3,time4);
//        List<TimeModel> collect = timeModels.stream().filter((timeModel -> compareTimeContain(timeModel, time1)))
//       .collect(Collectors.toList());
//        System.out.println(collect);
//
//        System.out.println(compareTimeIn(parse2,time2));
//        System.out.println(compareTimeIn(parse2,time3));
//        System.out.println(compareTimeIn(parse2,time4));

//        System.out.println(compareTimeIn(parse4,time4));


//        List<TimeModel> collect1 = timeModels.stream().filter((timeModel -> compareTimeIn(parse2, timeModel)))
//       .collect(Collectors.toList());
//        System.out.println(collect1);
//        System.out.println(after(parse2,parse1));
//
//        System.out.println(parse1.toString());
//
//
//    }

}
