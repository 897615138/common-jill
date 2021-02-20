package jill.common.model.util;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author Jill W
 * @date 2020/12/14
 */
@Data
@Builder
public class TableInfo1 {
    @ExcelProperty(index = 0, value = "mobile")
    private String mobile;

    @ExcelProperty(index = 1, value = "mobile md5")
    private String mobileMD5;

    @ExcelProperty(index = 2, value = "ip")
    private String ip;

    @ExcelProperty(index = 3, value = "date")
    private String date;
}
