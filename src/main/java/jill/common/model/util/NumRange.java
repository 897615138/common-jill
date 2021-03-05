package jill.common.model.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.ApiModelProperty;
import jill.common.enums.ComputeTypeEnum;
import jill.common.util.word.SimilarUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 数值范围
 *
 * @author Jill W
 * @date 2020/11/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NumRange {
    /**
     * 双目运算符范围
     */
    private static final Integer[] DOUBLE_RANGE = {5, 8};
    @ApiModelProperty(value = "数值1 单值运算符的值或多值运算符的小值")
    private Integer value1;
    @ApiModelProperty(value = "数值2 多值运算符的大值")
    private Integer value2;
//    @ApiModelProperty(value = "运算符枚举code（ 0等于，1 小于，2 大于, 3 小于等于，4 大于等于，5 A<X<B，6 A<=X<B，7 A<X<=B ，8 A<=X<=B ，9 like，10不等于,11 非空)）")
//    private Integer computeType;
    /**
     * 通过数值和计算类型返回数值范围List
     * 0等于，1 小于，2 大于, 3 小于等于，4 大于等于，5 A<X<B，6 A<=X<B，7 A<X<=B ，8 A<=X<=B ，9 like，10不等于,11 非空)）
     *
     * @param value1 单一数值或者小的数值
     * @param value2 大的数值
     * @param type 计算类型
     * @return 数值范围List
     */
    public static List<NumRange> getRanges(Integer value1, Integer value2, Integer type) {
        ArrayList<NumRange> numRanges = new ArrayList<>();
        if (ObjectUtil.equal(ComputeTypeEnum.E.getCode(), type)) {
            numRanges.add(NumRange.builder().value1(value1).value2(value1).build());
        }
        if (ObjectUtil.equal(ComputeTypeEnum.L.getCode(), type)) {
            numRanges.add(NumRange.builder().value1(0).value2(value1 - 1).build());
        }
        if (ObjectUtil.equal(ComputeTypeEnum.M.getCode(), type)) {
            numRanges.add(NumRange.builder().value1(value1 + 1).value2(100).build());
        }
        if (ObjectUtil.equal(ComputeTypeEnum.LE.getCode(), type)) {
            numRanges.add(NumRange.builder().value1(0).value2(value1).build());
        }
        if (ObjectUtil.equal(ComputeTypeEnum.ME.getCode(), type)) {
            numRanges.add(NumRange.builder().value1(value1).value2(100).build());
        }
        if (ObjectUtil.equal(ComputeTypeEnum.LL.getCode(), type)) {
            numRanges.add(NumRange.builder().value1(value1 + 1).value2(value2 - 1).build());
        }
        if (ObjectUtil.equal(ComputeTypeEnum.LE_L.getCode(), type)) {
            numRanges.add(NumRange.builder().value1(value1).value2(value2 - 1).build());
        }
        if (ObjectUtil.equal(ComputeTypeEnum.L_LE.getCode(), type)) {
            numRanges.add(NumRange.builder().value1(value1 + 1).value2(value2).build());
        }
        if (ObjectUtil.equal(ComputeTypeEnum.LE_LE.getCode(), type)) {
            numRanges.add(NumRange.builder().value1(value1).value2(value2).build());
        }
        if (ObjectUtil.equal(ComputeTypeEnum.NE.getCode(), type)) {
            numRanges.add(NumRange.builder().value1(0).value2(value1 - 1).build());
            numRanges.add(NumRange.builder().value1(value1 + 1).value2(100).build());
        }
        if (ObjectUtil.equal(ComputeTypeEnum.LIKE.getCode(), type)) {
            Set<Integer> similarNum = SimilarUtil.getSimilarNum(value1, 0, 100);
            similarNum.forEach(num -> numRanges.add(NumRange.builder().value1(num).value2(num).build()));
        }
        return numRanges;
    }


    /**
     * 范围有重合返回True
     * 有null值也返回True
     *
     * @param ranges 范围
     * @return 结果
     */
    public static Boolean hasCoincidence(List<NumRange> ranges) {
        ArrayList<Integer> integers = new ArrayList<>();
        for (NumRange range : ranges) {
            if (ObjectUtil.isNull(range)) {
                return Boolean.TRUE;
            }
            List<Integer> integerList = getIntegerList(range);
            if (ObjectUtil.isNull(integerList)) {
                return Boolean.TRUE;
            }
            if (CollUtil.containsAny(integers, integerList)) {
                return Boolean.TRUE;
            } else {
                integers = (ArrayList<Integer>) CollUtil.addAll(integers, integerList);
            }
        }
        return Boolean.FALSE;
    }

    /**
     * NumRange->List<Integer>
     * 范围为空就返回null
     *
     * @param range NumRange
     * @return List<Integer>
     */
    public static List<Integer> getIntegerList(NumRange range) {
        if (ObjectUtil.isNull(range)) {
            return Collections.emptyList();
        }
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = range.value1; i <= range.value2; i++) {
            integers.add(i);
        }
        return integers;
    }

    public static void main(String[] args) {
//        RangeSet<Integer> rangeSet = TreeRangeSet.create();
//        rangeSet.add(Range.closed(1, 10));
//        rangeSet.add(Range.closed(2, 11));
//        System.out.println(rangeSet);
//        Set<Range<Integer>> ranges = rangeSet.asRanges();
//        ranges.forEach(integerRange -> System.out.println(integerRange.lowerEndpoint()));
        NumRange build1 = NumRange.builder().value1(1).value2(10).build();
        NumRange build2 = NumRange.builder().value1(11).value2(11).build();
        List<NumRange> like = NumRange.getRanges(1, null, 9);
        System.out.println(like);
        ArrayList<NumRange> numRanges = CollUtil.newArrayList(build1, build2);
        Boolean aBoolean = NumRange.hasCoincidence(numRanges);
        System.out.println(aBoolean);
    }
}
