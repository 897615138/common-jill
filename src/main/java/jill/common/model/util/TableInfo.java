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
public class TableInfo {
    @ExcelProperty(index = 0, value = "类型")
    private String type;

    @ExcelProperty(index = 1, value = "现有字段")
    private String paramName;

    @ExcelProperty(index = 2, value = "表名")
    private String tableName;

    @ExcelProperty(index = 3, value = "现有字段逻辑")
    private String comment;
}
