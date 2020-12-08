package jill.common.model;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import jill.common.enums.ComputeTypeEnum;
import jill.common.util.word.SimilarUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * less<=value<=more
 *
 * @author Jill W
 * @date 2020/11/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NumRange {
    private Integer less;
    private Integer more;

    /**
     * 通过数值和计算类型返回数值范围
     *
     * @param less 单一数值或者小的数值
     * @param more 大的数值
     * @param type 计算类型
     * @return 数值范围List
     */
    public static List<NumRange> getRanges(Integer less, Integer more, Integer type) {
        ArrayList<NumRange> numRanges = new ArrayList<>();
        if (ObjectUtil.equal(ComputeTypeEnum.E.getCode(), type)) {
            numRanges.add(NumRange.builder().less(less).more(more).build());
        }
        if (ObjectUtil.equal(ComputeTypeEnum.L.getCode(), type)) {
            numRanges.add(NumRange.builder().less(0).more(less - 1).build());
        }
        if (ObjectUtil.equal(ComputeTypeEnum.M.getCode(), type)) {
            numRanges.add(NumRange.builder().less(less + 1).more(100).build());
        }
        if (ObjectUtil.equal(ComputeTypeEnum.LE.getCode(), type)) {
            numRanges.add(NumRange.builder().less(0).more(less).build());
        }
        if (ObjectUtil.equal(ComputeTypeEnum.ME.getCode(), type)) {
            numRanges.add(NumRange.builder().less(more).more(100).build());
        }
        if (ObjectUtil.equal(ComputeTypeEnum.LL.getCode(), type)) {
            numRanges.add(NumRange.builder().less(less + 1).more(more - 1).build());
        }
        if (ObjectUtil.equal(ComputeTypeEnum.LE_L.getCode(), type)) {
            numRanges.add(NumRange.builder().less(less).more(more - 1).build());
        }
        if (ObjectUtil.equal(ComputeTypeEnum.L_LE.getCode(), type)) {
            numRanges.add(NumRange.builder().less(less + 1).more(more).build());
        }
        if (ObjectUtil.equal(ComputeTypeEnum.LE_LE.getCode(), type)) {
            numRanges.add(NumRange.builder().less(less).more(more).build());
        }
        if (ObjectUtil.equal(ComputeTypeEnum.NE.getCode(), type)) {
            numRanges.add(NumRange.builder().less(0).more(less - 1).build());
            numRanges.add(NumRange.builder().less(less + 1).more(100).build());
        }
        if (ObjectUtil.equal(ComputeTypeEnum.LIKE.getCode(), type)) {
            Set<Integer> similarNum = SimilarUtil.getSimilarNum(less, 0, 100);
            similarNum.forEach(num -> numRanges.add(NumRange.builder().less(num).more(num).build()));
        }
        return numRanges;
    }


    /**
     * 范围有重合返回True
     *
     * @param ranges 范围
     * @return 结果
     */
    public static Boolean hasCoincidence(List<NumRange> ranges) {
        ArrayList<Integer> integers = new ArrayList<>();
        for (NumRange range : ranges) {
            List<Integer> integerList = getIntegerList(range);
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
     *
     * @param range NumRange
     * @return List<Integer>
     */
    public static List<Integer> getIntegerList(NumRange range) {
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = range.getLess(); i <= range.getMore(); i++) {
            integers.add(i);
        }
        return integers;
    }

    public static void main(String[] args) {
        ArrayList<NumRange> numRanges = new ArrayList<>();
        NumRange build = NumRange.builder().more(10).less(1).build();
        NumRange build1 = NumRange.builder().more(20).less(10).build();
        numRanges.add(build1);
        numRanges.add(build);
        Boolean aBoolean = hasCoincidence(numRanges);
        System.out.println(aBoolean);
    }
}
