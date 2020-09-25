/**
 * Copyright (C), 2012-2019, 杭州端点网络科技有限公司
 */
package jill.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 音频类型
 *
 * @author: <a href="mailto:xh168479@alibaba-inc.com">xiehong</a>
 * @date: 2019-08-26 13:52
 */
@Getter
@AllArgsConstructor
public enum VoiceType {

    //flac ape wav mp3 aac ogg wma

    MP3("mp3"),
    WAV("wav"),
    AAC("aac");

    private String code;

    public static VoiceType getEnum(String code) {
        for (VoiceType instance : values()) {
            if (Objects.equals(instance.getCode(), code)) {
                return instance;
            }
        }
        return null;
        // throw new IllegalArgumentException("unexpected VoiceType value: " + code);
    }
}