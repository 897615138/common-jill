/**
 * Copyright (C), 2012-2019, 杭州端点网络科技有限公司
 */
package jill.common.enums;

import cn.hutool.core.util.PageUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文件大类
 *
 * @author jill
 */
@Getter
@AllArgsConstructor
public enum FileType {
    /**
     * code 类型编码 desc 类型描述
     */
    FOLDER(1, "文件夹"),
    IMG(2, "图片"),
    GIF(3, "GIF"),
    VOICE(4, "音频"),
    VIDEO(5, "视频"),
    TEXT(6, "文本"),
    OTHER(7, "其他");

    private final Integer code;
    private final String desc;

    public static FileType getEnum(int code) {
        for (FileType instance : values()) {
            if (instance.getCode() == code) {
                return instance;
            }
        }
        return null;
//        throw new IllegalArgumentException("unexpected FileType value: " + code);
    }

    public static void main(String[] args) {
//        String s = FileType.FOLDER.toString();
//        System.out.println(FileType.FOLDER);
//        ArrayList<String> integers = new ArrayList<>();
//        integers.add("1");
//        integers.add("2");
//        String s1 = integers.toString();
//        System.out.println(s1);
//        String s2 = JSONUtil.toJsonStr(integers);
//        JSONArray objects = JSONUtil.parseArray(s2);
//        List<String> strings = objects.toList(String.class);
//        System.out.println(strings+"ok");
        int[] ints = PageUtil.transToStartEnd(2, 2);


        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }

    }
}