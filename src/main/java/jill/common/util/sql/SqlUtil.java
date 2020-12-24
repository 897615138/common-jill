package jill.common.util.sql;

import cn.hutool.core.util.StrUtil;
import jill.common.model.util.TableInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jill W
 * @date 2020/12/14
 */
public class SqlUtil {
    public static final Pattern TABLE = Pattern.compile("create table if not exists ");

    public static List<TableInfo> getData(File file) {
        String fileName = file.getName();
        System.out.println("======================文件名: " + fileName + "================================");
        List<TableInfo> tableInfos = new ArrayList<>();

        String sqlStringFromFile = getSqlStringFromFile(file);
        List<Integer> index = getTableIndex(sqlStringFromFile);
        if (index.size() == 1) {
            String substring = sqlStringFromFile.substring(index.get(0));
            TableInfo tableInfo = getTableInfo(substring);
            if (Objects.nonNull(tableInfo)) {
                tableInfos.add(tableInfo);
            }
        } else if (index.size() > 1) {
            for (int i = 0; i < index.size() - 1; i++) {
                String substring = sqlStringFromFile.substring(index.get(i), index.get(i + 1) - 26);
                TableInfo tableInfo = getTableInfo(substring);
                if (Objects.nonNull(tableInfo)) {
                    tableInfos.add(tableInfo);
                }
                System.out.println();
            }
        }
        System.out.println();
        return tableInfos;
    }

    /**
     * 获得创建表的sql的分块
     *
     * @param sqlStringFromFile sql字符串
     * @return indexList
     */
    public static List<Integer> getTableIndex(String sqlStringFromFile) {
        Matcher tableMatcher = TABLE.matcher(sqlStringFromFile);
        List<Integer> index = new ArrayList<>();
        while (tableMatcher.find()) {
            index.add(tableMatcher.end());
        }
        return index;
    }

    /**
     * 读取sql文件中的内容返回字符串
     *
     * @param file .sql文件
     * @return 内容字符串
     */
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

    /**
     * 获取表信息
     *
     * @param substring 含有表信息的substring
     * @return 表信息
     */
    public static TableInfo getTableInfo(String substring) {
        String tableName = substring.substring(0, substring.indexOf(" ") - 3);
        //字段提取
        substring = substring.substring(substring.indexOf(" "));
        String[] params = substring.split(",\n");
        for (String param : params) {
            if (StrUtil.isNotBlank(param)) {
                String[] splitByBlank = param.split("[ ]+");
                boolean notFindName = true;
                String paramName = null;
                String comment = null;
                for (int i1 = 0; i1 < splitByBlank.length; i1++) {
                    String s = splitByBlank[i1];
                    if (notFindName && StrUtil.isNotBlank(s)) {
                        paramName = s;
                        System.out.print("字段名:" + paramName + ",");
                        notFindName = false;
                    }
                    if (StrUtil.equals(s, "comment")) {
                        comment = splitByBlank[i1 + 1].replace("'", "")
                                .replace(")", "")
                                .replace("\n", "")
                                .replace(";create", "");
                        System.out.print("注释:" + comment + "/");
                        break;
                    }
                }
                return TableInfo.builder().tableName(tableName).comment(comment).type("业务").paramName(paramName).build();
            }
        }
        return null;
    }

    /**
     * 获取表信息 设置默认注释
     *
     * @param substring      含有表信息的substring
     * @param defaultComment 默认注释 如果为null 就使用字段名作为注释
     * @return 表信息
     */
    public static TableInfo getTableInfo(String substring, String defaultComment) {
        String tableName = substring.substring(0, substring.indexOf(" ") - 3);
        //字段提取
        substring = substring.substring(substring.indexOf(" "));
        String[] params = substring.split(",\n");
        for (String param : params) {
            if (StrUtil.isNotBlank(param)) {
                String[] splitByBlank = param.split("[ ]+");
                boolean hasComment = false;
                boolean notFindName = true;
                String paramName = null;
                String comment = null;
                for (int i1 = 0; i1 < splitByBlank.length; i1++) {
                    String s = splitByBlank[i1];
                    if (notFindName && StrUtil.isNotBlank(s)) {
                        paramName = s;
                        System.out.print("字段名:" + paramName + ",");
                        notFindName = false;
                    }
                    if (StrUtil.equals(s, "comment")) {
                        comment = splitByBlank[i1 + 1].replace("'", "")
                                .replace(")", "")
                                .replace("\n", "")
                                .replace(";create", "");
                        System.out.print("注释:" + comment + "/");
                        hasComment = true;
                        break;
                    }
                }
                if (!hasComment) {
                    if (Objects.isNull(defaultComment)) {
                        comment = paramName;
                    } else {
                        comment = defaultComment;
                    }
                    System.out.print("注释:" + comment + "/");
                }
                return TableInfo.builder().tableName(tableName).comment(comment).type("业务").paramName(paramName).build();
            }
        }
        return null;
    }
}
