package jill.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 音频类型
 *
 * @author jill
 */
@Getter
@AllArgsConstructor
public enum VoiceType {

    //flac ape wav mp3 aac ogg wma

    MP3("mp3"),
    WAV("wav"),
    AAC("aac");

    private final String code;

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