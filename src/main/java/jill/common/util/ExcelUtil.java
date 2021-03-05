package jill.common.util;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import jill.common.model.util.TableInfo;
import jill.common.model.util.TableInfo1;
import jill.common.model.util.TableInfo2;
import jill.common.util.sql.SqlUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author jill
 */
@SuppressWarnings("ALL")
@Slf4j
public class ExcelUtil {
    private static final ReadSheet initReadSheet;
    private static final WriteSheet initWriteSheet;

    static {
        initReadSheet = new ReadSheet(1, "sheet-name");
        //设置自适应宽度
        initReadSheet.setAutoTrim(Boolean.TRUE);
        initWriteSheet = new WriteSheet();
        initWriteSheet.setSheetName("sheet-write");
    }

    /**
     * 读取少于1000行数据
     *
     * @param filePath 文件绝对路径
     * @return List
     */
    public static List<Object> readLessThan1000Row(String filePath) {
        return readLessThan1000RowByReadSheet(filePath, null);
    }

    /**
     * 读小于1000行数据, 带样式
     *
     * @param filePath      文件绝对路径
     * @param initReadSheet ：
     * @param sheetNo:      sheet页码，默认为1
     * @param headLineMun:  从第几行开始读取数据，默认为0, 表示从第一行开始读取
     * @param clazz:        返回数据List<Object> 中Object的类名
     * @return 列表
     */
    private static List<Object> readLessThan1000RowByReadSheet(String filePath, ReadSheet sheet) {
        if (StrUtil.isBlank(filePath)) {
            return ListUtil.list(false);
        }
        sheet = sheet != null ? sheet : initReadSheet;
        InputStream fileStream = null;
        List<Object> objects = ListUtil.list(false);
        try {
            //inputStream = new FileInputStream(filePath)
            fileStream = new FileInputStream(filePath);
            objects = EasyExcel.read(fileStream).sheet(sheet.getSheetName()).doReadSync();
        } catch (FileNotFoundException e) {
            log.info("找不到文件或文件路径错误, 文件：{}", filePath);
        } finally {
            try {
                if (fileStream != null) {
                    fileStream.close();
                }
            } catch (IOException e) {
                log.info("excel文件读取失败, 失败原因：{}", e.getMessage());
            }
        }
        return objects;
    }

    /**
     * 读大于1000行数据
     *
     * @param filePath 文件觉得路径
     * @return 数据
     */
    public static List<Object> readMoreThan1000Row(String filePath) {
        return readMoreThan1000RowByReadSheet(filePath, null);
    }

    /**
     * 读大于1000行数据, 带样式
     *
     * @param filePath 文件路径
     * @return List
     */
    private static List<Object> readMoreThan1000RowByReadSheet(String filePath, ReadSheet sheet) {
        if (StrUtil.isNotBlank(filePath)) {
            return ListUtil.list(false);
        }
        sheet = sheet != null ? sheet : initReadSheet;
        InputStream fileStream = null;
        try {
            fileStream = new FileInputStream(filePath);
            ExcelListener excelListener = new ExcelListener();
            EasyExcel.read(fileStream, excelListener).sheet(sheet.getSheetNo()).doRead();
            return excelListener.getData();
        } catch (FileNotFoundException e) {
            log.error("找不到文件或文件路径错误, 文件：{}", filePath);
        } finally {
            try {
                if (fileStream != null) {
                    fileStream.close();
                }
            } catch (IOException e) {
                log.error("excel文件读取失败, 失败原因：{}", e.getMessage());
            }
        }
        return Collections.emptyList();
    }

    /**
     * 生成excel
     *
     * @param filePath 绝对路径, 如：/home/jill/Downloads/aaa.xlsx
     * @param data     数据源
     * @param head     表头
     */
    public static void writeBySimple(String filePath, List<List<Object>> data, List<String> head) {
        writeSimpleByReadSheet(filePath, data, head, null);
    }

    /**
     * 生成excel
     *
     * @param filePath 绝对路径, 如：/home/jill/Downloads/aaa.xlsx
     * @param data     数据源
     * @param sheet    excel页面样式
     * @param head     表头
     */
    private static void writeSimpleByReadSheet(String filePath, List<List<Object>> data, List<String> head,
                                               WriteSheet sheet) {
        sheet = (sheet != null) ? sheet : initWriteSheet;
        if (head != null) {
            List<List<String>> list = new ArrayList<>();
            head.forEach(h -> list.add(Collections.singletonList(h)));
            sheet.setHead(list);
        }
        OutputStream outputStream = null;
        ExcelWriter writer = null;
        try {
            outputStream = new FileOutputStream(filePath);
            writer = EasyExcelFactory.getWriter(outputStream);
            writer.write(data, sheet);
        } catch (FileNotFoundException e) {
            log.error("找不到文件或文件路径错误, 文件：{}", filePath);
        } finally {
            try {
                if (writer != null) {
                    writer.finish();
                }
                if (outputStream != null) {
                    outputStream.close();
                }

            } catch (IOException e) {
                log.error("excel文件导出失败, 失败原因：{}", e.getMessage());
            }
        }
    }

    /**
     * 生成excel
     *
     * @param filePath 绝对路径, 如：/home/jill/Downloads/aaa.xlsx
     * @param data     数据源
     */
    public static void writeWithTemplate(String filePath, List<? extends BaseRowModel> data) {
        writeWithTemplateAndReadSheet(filePath, data, null);
    }

    /**
     * 生成excel
     *
     * @param filePath 绝对路径, 如：/home/jill/Downloads/aaa.xlsx
     * @param data     数据源
     * @param sheet    excel页面样式
     */
    private static void writeWithTemplateAndReadSheet(String filePath, List<? extends BaseRowModel> data,
                                                      WriteSheet sheet) {
        if (CollectionUtil.isEmpty(data)) {
            return;
        }
        sheet = (sheet != null) ? sheet : initWriteSheet;
        sheet.setClazz(data.get(0).getClass());
        OutputStream outputStream = null;
        ExcelWriter writer = null;
        try {
            outputStream = new FileOutputStream(filePath);
            writer = EasyExcelFactory.getWriter(outputStream);
            writer.write(data, sheet);
        } catch (FileNotFoundException e) {
            log.error("找不到文件或文件路径错误, 文件：{}", filePath);
        } finally {
            try {
                if (writer != null) {
                    writer.finish();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                log.error("excel文件导出失败, 失败原因：{}", e.getMessage());
            }
        }
    }

    /**
     * 生成多ReadSheet的excel
     *
     * @param filePath                绝对路径, 如：/home/jill/Downloads/aaa.xlsx
     * @param multipleSheetProperties m
     */
    public static void writeWithMultipleSheet(String filePath, List<MultipleSheetProperty> multipleSheetProperties) {
        if (CollectionUtil.isEmpty(multipleSheetProperties)) {
            return;
        }
        OutputStream outputStream = null;
        ExcelWriter writer = null;
        try {
            outputStream = new FileOutputStream(filePath);
            writer = EasyExcelFactory.getWriter(outputStream);
            for (MultipleSheetProperty multipleSheelaProphet : multipleSheetProperties) {
                WriteSheet sheet =
                        multipleSheelaProphet.getSheet() != null ? multipleSheelaProphet.getSheet() : initWriteSheet;
                if (!CollectionUtil.isEmpty(multipleSheelaProphet.getData())) {
                    sheet.setClazz(multipleSheelaProphet.getData().get(0).getClass());
                }
                writer.write(multipleSheelaProphet.getData(), sheet);
            }
        } catch (FileNotFoundException e) {
            log.error("找不到文件或文件路径错误, 文件：{}", filePath);
        } finally {
            try {
                if (writer != null) {
                    writer.finish();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                log.error("excel文件导出失败, 失败原因：{}", e.getMessage());
            }
        }
    }

    public static void writeExcel(String name, List<TableInfo> data) {
        String fileName = SqlUtil.class.getResource("/").getPath() + name + ".xlsx";
        File file = cn.hutool.core.io.FileUtil.file(fileName);
        if (Objects.isNull(file)) {
            file = FileUtil.newFile(fileName);
            System.out.println(file);
        }
        System.out.println(file);
        EasyExcel.write(fileName, TableInfo.class).sheet(name).doWrite(data);
    }

    public static void writeExcel1(String name, List<TableInfo1> data) {
        String fileName = SqlUtil.class.getResource("/").getPath() + name + ".xlsx";
        File file = cn.hutool.core.io.FileUtil.file(fileName);
        if (Objects.isNull(file)) {
            file = FileUtil.newFile(fileName);
            System.out.println(file);
        }
        System.out.println(file);
        EasyExcel.write(fileName, TableInfo.class).sheet(name).doWrite(data);
    }

//    public static void main(String[] args) {
//        TableInfo build = TableInfo.builder().tableName("test").comment("test").paramName("test").build();
//        List<TableInfo> tableInfos = new ArrayList<>();
//        tableInfos.add(build);
//        writeExcel("test", tableInfos);
//    }
//public static void main(String[] args) {
//    File file = FilesUtil.getResource("risk.xlsx");
//    ExcelReaderBuilder read = EasyExcel.read(file, TableInfo2.class, new ExcelListener());
//    read.sheet(0).doRead();
//}


    @Data
    private static class MultipleSheetProperty {

        private List<? extends BaseRowModel> data;

        private WriteSheet sheet;
    }

    /**
     * 解析监听器，
     * 每解析一行会回调invoke()方法。
     * 整个excel解析结束会执行doAfterAllAnalysed()方法
     */
    @Getter
    @Setter
    public static class ExcelListener extends AnalysisEventListener {

        private List<Object> data = new ArrayList<>();
        List<TableInfo1> tableInfos = new ArrayList<>();

        /**
         * 逐行解析
         * object : 当前行的数据
         */
        @Override
        public void invoke(Object object, AnalysisContext context) {
            //当前行
            // context.getCurrentRowNum()
            if (object != null) {
                data.add(object);
                TableInfo2 tableInfo = (TableInfo2) object;
                System.out.println(tableInfo);
                //解析结束销毁不用的资源

                TableInfo1 build = TableInfo1.builder()
                        .date(tableInfo.getDate())
                        .ip(tableInfo.getIp())
                        .mobile(Md5Util.getMobileMd5(tableInfo.getMobileMD5()))
                        .mobileMD5(tableInfo.getMobileMD5()).build();
                tableInfos.add(build);
            }
        }


        /**
         * 解析完所有数据后会调用该方法
         */
        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            writeExcel1("test", tableInfos);
        }

    }
}
