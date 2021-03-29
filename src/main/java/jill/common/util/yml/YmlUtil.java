package jill.common.util.yml;

import cn.hutool.core.util.StrUtil;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import jill.common.util.FilesUtil;
import jill.common.util.yml.model.ControllerClass;
import jill.common.util.yml.model.ControllerMethod;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Jill W
 * @date 2021/03/24
 */
@Slf4j
public class YmlUtil {
    private static final HashSet<String> ignoreSetByName = new HashSet<>();

    static {
        ignoreSetByName.addAll(Arrays.asList("getClass", "notify", "wait", "equals", "toString", "hashCode", "notifyAll"));
    }
    public static Map<String, ControllerClass> getControllerMap(String fileName) {
        YamlReader reader;
        HashMap<String, ControllerClass> controllerMap = new HashMap<>();
        try {
            reader = new YamlReader(new FileReader(FilesUtil.getResource(fileName)));
            while (true) {
                Map contact = (Map) reader.read();
                if (contact == null) {
                    break;
                }
                Map config = (Map) contact.get("config");
                ArrayList operations = (ArrayList) config.get("operations");

                operations.forEach(operation -> {
                    LinkedHashMap operation1 = (LinkedHashMap) operation;
                    String packageName = (String) operation1.get("package-name");
                    ArrayList classes = (ArrayList) operation1.get("classes");
                    classes.forEach(clazz -> {
                        LinkedHashMap clazz1 = (LinkedHashMap) clazz;
                        ControllerClass controllerClass = ControllerClass.builder().className(String.valueOf(clazz1.get("className")))
                                .appName(String.valueOf(clazz1.get("app-name")))
                                .appCode(String.valueOf(clazz1.get("app-code")))
                                .build();
                        LinkedHashMap<String, ControllerMethod> newMethods = new LinkedHashMap<>();
                        LinkedHashMap methods = (LinkedHashMap) clazz1.get("methods");
                        methods.forEach((k, v) -> {
                            LinkedHashMap v1 = (LinkedHashMap) v;
                            ControllerMethod newMethod = ControllerMethod.builder().key(String.valueOf(k))
                                    .code(String.valueOf(v1.get("code")))
                                    .extraArgs(v1.get("extra-format") == null ? null : String.valueOf(v1.get("extra-format")))
                                    .extraFormat(v1.get("extra-args") == null ? null : String.valueOf(v1.get("extra-args")))
                                    .handler(v1.get("handler") == null ? null : String.valueOf(v1.get("handler")))
                                    .build();
                            newMethods.put(String.valueOf(v1.get("code")), newMethod);
                        });
                        controllerClass.setMethods(newMethods);
                        controllerMap.put(controllerClass.getClassName(), controllerClass);
                    });
                });
            }
        } catch (FileNotFoundException | YamlException e) {
            e.printStackTrace();
        }
        return controllerMap;
    }


    public static void main(String[] args) {
        printResult("operation-log.yml", "marketing-admin-starter", "com.mobil.marketing.admin.log", "com.mobil.marketing.admin.controller", "marketing-admin"
                , "com.mobil.marketing.admin.controller.MobilActivitiesController.createOrUpdate");
    }

    /**
     * print输出缺失部分的模板内容与读取文件失败的controller需要自行检查
     *
     * @param logFileName           日志文件名，默认为"operation-log.yml"
     * @param logFileModule         日志文件所在模块
     * @param logHandlerPackageName 日志Handler包名"com.mobil.marketing.admin.log"
     * @param controllerPackageName 控制台包名，例如"com.mobil.marketing.admin.controller"
     * @param controllerModuleName  控制台所在模块
     * @param ignore                忽略的方法,全名 例如"com.mobil.marketing.admin.controller.MobilActivitiesController.createOrUpdate"
     */
    public static void printResult(String logFileName, String logFileModule, String logHandlerPackageName, String controllerPackageName, String controllerModuleName, String... ignore) {
        //默认值
        if (StrUtil.isBlank(logFileName)) {
            logFileName = "operation-log.yml";
        }
        Map<String, ControllerClass> controllerMap = getControllerMap(logFileName);
        System.out.println("[NOTE]:如果有任何yml解析的报错，或其他不知名报错，检查yml文件");
        Map<String, Class> classes = FilesUtil.getClasses(controllerPackageName, controllerModuleName);
//        StringBuilder miss = new StringBuilder();
        StringBuilder special = new StringBuilder();
        StringBuilder methodStr = new StringBuilder();
        //抽象类方法被继承不包括在内
        checkAbstractController(classes.get(controllerPackageName + ".AbstractController"));
        List<String> ignoreSetByPackageName = Arrays.asList(ignore);
        classes.forEach((k, v) -> {
            if (Objects.isNull(v) || StrUtil.isBlank(k)) {
                log.info("has null infos of classes");
                return;
            }
            String javaName = k.substring(k.lastIndexOf(".") + 1);
            if ("AbstractController".equals(javaName)) {
                return;
            }
            List<Method> methods = new ArrayList<>();
            try {
                methods = Arrays.stream(v.getMethods()).filter(method -> {
                    if (Objects.isNull(method)) {
                        return false;
                    }
                    String name = method.getName();
                    return StrUtil.isNotBlank(name) && !ignoreSetByName.contains(name) && !ignoreSetByPackageName.contains(k + "." + name);
                }).collect(Collectors.toList());
                if (methods.isEmpty()) {
                    return;
                }
            } catch (NoClassDefFoundError e) {
                special.append(k).append("\n");
            }

//            miss.append(k).append("\n");
            ControllerClass controllerClass = controllerMap.get(javaName);
            if (Objects.isNull(controllerClass)) {
                appendHead(methodStr, k, javaName);
                for (Method method : methods) {
                    appendMethod(methodStr, method, logHandlerPackageName, javaName);
                }
            } else {
                LinkedHashMap<String, ControllerMethod> havingMethods = controllerClass.getMethods();
                boolean hasHeaders = false;
                for (Method method : methods) {
                    ControllerMethod controllerMethod = havingMethods.get(method.getName());
                    if (Objects.isNull(controllerMethod)) {
                        if (!hasHeaders) {
                            appendHead(methodStr, k, javaName);
                            hasHeaders = true;
                        }
                        String name = method.getName();
                        String methodName = getMethodName(method);
                        methodStr.append("\t").append("\t").append(name).append(":").append("\n")
                                .append("\t").append("\t").append("\t").append("code: ").append(name).append("\n")
                                .append("\t").append("\t").append("\t").append("name: ").append(methodName).append("\n")
                                .append("\t").append("\t").append("\t").append("extra-format: ").append("\"【%s】\"").append("\n")
                                .append("\t").append("\t").append("\t").append("extra-args: ").append(" ").append("\n")
                                .append("\t").append("\t").append("\t").append("handler: ").append(" ").append(logHandlerPackageName)
                                .append(".").append(javaName, 0, javaName.indexOf("Controller") - 1).append(name)
                                .append("LogHandler").append("\n");
//                        miss.append(k).append(".").append(method.getName()).append("\n");
                    }
                }
            }
        });
//        System.out.println("miss");
//        System.out.println(miss.toString());
        System.out.println("methods 缺失方法 ");
        System.out.println(methodStr);
        System.out.println("special 反射失败 自行检查");
        System.out.println(special.toString());
    }

    /**
     * 增加yml方法内容
     *
     * @param methodStr             增加到
     * @param method                方法
     * @param logHandlerPackageName 日志处理类包名
     * @param javaName              类名
     */
    private static void appendMethod(StringBuilder methodStr, Method method, String logHandlerPackageName, String javaName) {
        String name = method.getName();
        String methodName = getMethodName(method);
//                    miss.append(k).append(".").append(name).append("\n");
        methodStr.append("\t").append("\t").append(name).append(":").append("\n")
                .append("\t").append("\t").append("\t").append("code: ").append(name).append("\n")
                .append("\t").append("\t").append("\t").append("name: ").append(methodName).append("\n")
                .append("\t").append("\t").append("\t").append("extra-format: ").append("\"").append(methodName).append("【%s】\"").append("\n")
                .append("\t").append("\t").append("\t").append("extra-args: ").append(" ").append("\n")
                .append("\t").append("\t").append("\t").append("handler: ").append(" ").append(logHandlerPackageName)
                .append(".").append(javaName, 0, javaName.indexOf("Controller") - 1).append(name)
                .append("LogHandler").append("\n");
    }

    /**
     * 增加yml头部内容
     *
     * @param methodStr   增加到
     * @param packageName 包名
     * @param javaName    类名
     */
    private static void appendHead(StringBuilder methodStr, String packageName, String javaName) {
        String appName = getAppName(packageName);
        methodStr.append(packageName).append("\n");
        methodStr.append("- className: \"").append(javaName).append("\"").append("\n")
                .append("\t").append("app-code: ").append(javaName).append("\n")
                .append("\t").append("app-name: ").append(appName).append("\n")
                .append("\t").append("methods: ").append("\n");
    }
    /**
     * 获得控制台注释
     *
     * @param controllerName 控制台名称
     * @return 控制台注释
     */
    private static String getAppName(String controllerName) {
        try {
            Class<?> aClass = Thread.currentThread().getContextClassLoader().loadClass(controllerName);
            if (Objects.nonNull(aClass)) {
                Api annotation = aClass.getAnnotation(Api.class);
                if (Objects.nonNull(annotation)) {
                    String description = annotation.description();
                    String value = annotation.value();
                    if (StrUtil.isNotBlank(description)){
                        return  description;
                    }else if(StrUtil.isNotBlank(value)){
                        return value;
                    }
                }
            }
            return controllerName;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取方法释义注释
     *
     * @param method 方法
     * @return 注释
     */
    private static String getMethodName(Method method) {
        String methodName;
        ApiOperation annotation1 = method.getAnnotation(ApiOperation.class);
        ApiModelProperty annotation2 = method.getAnnotation(ApiModelProperty.class);
        if (Objects.nonNull(annotation1)) {
            methodName = annotation1.value();
        } else {
            if (Objects.nonNull(annotation2)) {
                methodName = annotation2.value();
            } else {
                methodName = method.getName();
            }
        }
        return methodName;
    }

    /**
     * 抽象控制台方法剥离
     *
     * @param abstractController abstractController
     */
    private static void checkAbstractController(Class abstractController) {
        if (Objects.nonNull(abstractController)) {
            Arrays.stream(abstractController.getMethods()).forEach(method -> {
                if (Objects.nonNull(method)) {
                    String name = method.getName();
                    if (StrUtil.isNotBlank(name)) {
                        ignoreSetByName.add(name);
                    }
                }
            });
        }
    }
}
