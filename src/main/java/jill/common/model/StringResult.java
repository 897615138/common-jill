package jill.common.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

/**
 * @author Jill W
 * @date 2020/11/04
 */
@Data
@Builder
@ToString
public class StringResult {
    /**
     * 匹配序号 从0开始
     */
    Integer matchIndex;
    /**
     * 字符串的值
     */
    String stringValue;
    /**
     * 起始坐标
     */
    Integer startIndex;
    /**
     * 结束坐标
     */
    Integer endIndex;
}
