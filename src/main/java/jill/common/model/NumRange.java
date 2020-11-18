package jill.common.model;

import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jill W
 * @date 2020/11/18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NumRange {
    private Integer value1;
    private Integer value2;
    /**
     * 数据是否不合规
     *
     * @return 不合规返回true
     */
    public Boolean isWrong() {
        //区间范围为空
        if (ObjectUtil.isNull(this.value1) && ObjectUtil.isNull(this.value2)) {
            return true;
        }
        //大值小于小值
        if (ObjectUtil.isNotNull(this.value1)&&ObjectUtil.isNotNull(this.value2)){
            return this.value2<this.value1;
        }
        return false;
    }
    public Boolean isMatched(Integer value) {
        Integer less = this.value1;
        Integer more = this.value2;
        //不合规的设置
        if (this.isWrong()) {
            return false;
        }
        //less <= x
        if (ObjectUtil.isNull(more)) {
            return less <= value;
        }
        //x <= more
        if (ObjectUtil.isNull(less)) {
            return value <= more;
        }
        //less <=x <=more
        return less <= value && value <= more;
    }
    public static void main(String[] args) {
        //50<=x
        NumRange build1 = NumRange.builder().value1(50).build();
        //false
        System.out.println(build1.isMatched(49));
        //true
        System.out.println(build1.isMatched(50));
        System.out.println(build1.isMatched(51));
        //x<=50
        NumRange build2 = NumRange.builder().value2(50).build();
        //true
        System.out.println(build2.isMatched(49));
        System.out.println(build2.isMatched(50));
        //false
        System.out.println(build2.isMatched(51));
        //49<=x<=51
        NumRange build3 = NumRange.builder().value1(49).value2(51).build();
        //false
        System.out.println(build3.isMatched(47));
        System.out.println(build3.isMatched(52));
        //true
        System.out.println(build3.isMatched(50));
    }
}
