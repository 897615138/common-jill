package jill.common.model.util;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jill W
 * @date 2020/12/14
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TableInfo2 {

    @ExcelProperty(index = 0, value = "mobile md5")
    private String mobileMD5;

    @ExcelProperty(index = 1, value = "ip")
    private String ip;

    @ExcelProperty(index = 2, value = "date")
    private String date;
}
