package jill;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jill W
 * @date 2020/12/17
 */
public class Test {
    public static void main(String[] args) {
//        //返回与当前线程关联的语言环境
//        Locale locale = LocaleContextHolder.getLocale();
//        System.out.println(locale);
//        System.out.println("shjbjaf".split("_")[0]);
//       String json="{\n" +
//               "  \"userInfo\": \"{\\\"isEmailNotice\\\":0,\\\"gender\\\":1,\\\"isAlipayNotice\\\":0,\\\"typeName\\\":\\\"普通会员\\\",\\\"isWechatMiniProgramNotice\\\":0,\\\"type\\\":0,\\\"thirdInfos\\\":[{\\\"fromName\\\":\\\"微信商城\\\",\\\"from\\\":\\\"WXMALL\\\"},{\\\"fromName\\\":\\\"天猫商城\\\",\\\"from\\\":\\\"TMALL\\\"},{\\\"fromName\\\":\\\"京东商城\\\",\\\"from\\\":\\\"JD\\\"}],\\\"isFirst\\\":0,\\\"createdAt\\\":1610956861000,\\\"fromName\\\":\\\"微信小程序\\\",\\\"nickname\\\":\\\"\\\",\\\"statusName\\\":\\\"生效中\\\",\\\"carItems\\\":[],\\\"from\\\":\\\"WXAPP\\\",\\\"id\\\":100004959,\\\"updatedAt\\\":1610956868000,\\\"isAccount\\\":1,\\\"mobile\\\":\\\"152****6197\\\",\\\"lastLocation\\\":\\\"南京市\\\",\\\"avatar\\\":\\\"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJHDicQaiczWgSXEvhx0YyfDnhyjH8Eul2aiaibjnE9ErVEjkjUMpJHesmqTdSuWcpMPP7oF3tWWvMOWw/132\\\",\\\"userId\\\":100004959,\\\"genderName\\\":\\\"男\\\",\\\"isSmsNotice\\\":1,\\\"lastLoginAt\\\":1610956862000,\\\"isWechatOfficialAccountNotice\\\":0,\\\"outerId\\\":\\\"6000000000100004959\\\",\\\"registerAt\\\":1610956862000,\\\"status\\\":1}\",\n" +
//               "  \"newMemberPhone\": \"15261986197\",\n" +
//               "  \"newMemberId\": \"100004959\"\n" +
//               "}";
//        JSONObject jsonObject = JSONUtil.parseObj(json);
//        String newMemberPhone = (String)jsonObject.get("newMemberPhone");
//        String newMemberId = (String) jsonObject.get("newMemberId");
//        String userInfoJson = String.valueOf(jsonObject.get("userInfo"));
//        JSONObject userInfo = JSONUtil.parseObj(userInfoJson);
//        String registerAtString = String.valueOf(userInfo.get("registerAt"));
//        String registerAt = DateUtil.parseTimestampThirteen(registerAtString);
//        System.out.println(registerAt);


//        String time = "Wed Jan 27 16:53:49 CST 2021";
        Date date = new Date("Wed Jan 27 16:53:49 CST 2021");
//        long time1 = date.getTime();
//        long l = time1 - 52445000L;
//        Date d = new Date(l);
//        try {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String s = dateFormat.parse(d.toString()).toString();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        System.out.println(date.getTime());
        date.setHours(date.getHours()-14);
        DateTime parse = DateUtil.parse(String.valueOf(date));
//        String s = DateUtil.formatDateTime(date);
//        System.out.println(s);
        System.out.println(parse.toString());
//        System.out.println(1611956600000L - 1611904155000L);


//        try {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date utilDate = dateFormat.parse(date.toString());
//            System.out.println(utilDate.toString());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        long time1 = date.getTime();
//        DateTime date1 = DateUtil.date(date);
//        String s2 = date1.toString();
//        System.out.println(s2);
//
//        String s1 = date.toString();
//        System.out.println(s1);
//        String s = String.valueOf(date);
//        System.out.println(s);

//        DateTime parse = DateUtil.parse(String.valueOf());
//            System.out.println(parse.toString());


    }


}
