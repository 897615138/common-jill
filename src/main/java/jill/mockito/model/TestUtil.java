package jill.mockito.model;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JillW
 * @date 2020/10/10
 */
public class TestUtil {
    public static void main(String[] args) {
        Game game1 = Game.builder().type("1").rate(2).build();
        Game game2 = Game.builder().type("2").rate(3).build();
        ArrayList<Game> games = CollUtil.newArrayList(game1, game2);
        String s = JSONUtil.toJsonStr(games);
        System.out.println(s);
        JSONArray objects = JSONUtil.parseArray(s);
        System.out.println(objects);
//        ArrayList<Integer> strings = CollUtil.newArrayList(1);
//        System.out.println(strings);
//        System.out.println(EnumUtil.contains(EmBannerPortalType.class,"WX" ));
        List<Game> games1 = objects.toList(Game.class);
        System.out.println(game1);

        DateTime dateTime = DateUtil.parseCST("Tue Oct 13 14:00:00 CST 2020");
        String s1 = DateUtil.formatDateTime(dateTime);
        System.out.println(s1);
    }


}
