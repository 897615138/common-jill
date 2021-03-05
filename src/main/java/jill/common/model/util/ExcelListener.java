package jill.common.model.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author Jill W
 * @date 2021/01/05
 */
public class ExcelListener extends AnalysisEventListener<TableInfo2> {
    //一行一行读取excel的内容,横着一行一行读
    @Override
    public void invoke(TableInfo2 demo, AnalysisContext analysisContext) {
        System.out.println("**************"+demo);
    }

    //读取表头之中的内容
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头:"+headMap);
    }
    //读完之后做的事情
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}