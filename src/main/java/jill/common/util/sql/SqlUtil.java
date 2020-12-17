package jill.common.util.sql;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import jill.common.model.TableInfo;
import jill.common.util.ExcelUtil;
import jill.common.util.FilesUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jill W
 * @date 2020/12/14
 */
public class SqlUtil {
    public static Pattern table = Pattern.compile("create table if not exists ");

    public static List<TableInfo> getData(File file) {
        String fileName = file.getName();
        List<TableInfo> tableInfos = new ArrayList<>();
        System.out.println("======================文件名: " + fileName + "================================");
        String sqlStringFromFile = getSqlStringFromFile(file);
        Matcher tableMatcher = table.matcher(sqlStringFromFile);
        List<Integer> index = new ArrayList<>();
        while (tableMatcher.find()) {
            index.add(tableMatcher.end());
        }
        for (int i = 0; i < index.size() - 1; i++) {
            String substring = sqlStringFromFile.substring(index.get(i), index.get(i + 1) - 26);
            String tableName = substring.substring(0, substring.indexOf(" ") - 3);
            System.out.println("--------------" + fileName + ":" + tableName + "--------------------------");
//                System.out.println("表名:" + tableName);
            //字段提取
            substring = substring.substring(substring.indexOf(" "));
            String[] params = substring.split(",\n");
            for (String param : params) {
                if (StrUtil.isNotBlank(param)
//                            &&param.length()>3
                ) {
                    String[] s1 = param.split("[ ]+");
                    boolean hasComment = false;
                    boolean notFindName = true;
                    String paramName = null;
                    String comment = null;
                    for (int i1 = 0; i1 < s1.length; i1++) {
                        String s = s1[i1];
                        if (notFindName && StrUtil.isNotBlank(s)) {
                            paramName = s;
                            System.out.print("字段名:" + paramName + ",");
                            notFindName = false;
                        }
                        if (StrUtil.equals(s, "comment")) {
                            comment = s1[i1 + 1].replace("'", "")
                                    .replace(")", "")
                                    .replace("\n", "")
                                    .replace(";create", "");
                            System.out.print("注释:" + comment + "/");
//                            hasComment = true;
                            break;
                        }
                    }
//                        if (!hasComment) {
//                            comment = paramName;
//                            System.out.print("注释:" + comment + "/");
//                        }
                    TableInfo info = TableInfo.builder().tableName(tableName).comment(comment).type("业务").paramName(paramName).build();
                    tableInfos.add(info);
                }
            }
            System.out.println();
        }
        System.out.println();
        return tableInfos;
    }

    public static void main(String[] args) {
//        File file = new File(SqlUtil.class.getResource("/ULP_sql.zip").getPath());
        File file = FilesUtil.getResource("/ULP_sql.zip");
        File unzip = ZipUtil.unzip(file);
        File[] files = unzip.listFiles();
        assert files != null;
        for (File sqlFile : files) {
            List<TableInfo> data = getData(sqlFile);
            ExcelUtil.writeExcel(sqlFile.getName(), data);
        }
    }

    public static String getSqlStringFromFile(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(file);
            try (LineNumberReader reader = new LineNumberReader(new BufferedReader(fileReader))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append(System.getProperty("line.separator"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
