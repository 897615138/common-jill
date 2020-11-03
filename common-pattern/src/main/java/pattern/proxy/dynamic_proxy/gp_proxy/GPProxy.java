package pattern.proxy.dynamic_proxy.gp_proxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 用来生成源代码的工具类
 *
 * @author Jill
 */
public
class GPProxy {
    /**
     * 换行符
     */
    private static final String LN = "\r\n";
    private static final Map<Class<? extends Integer>, Class<? extends Integer>> MAPPINGS = new HashMap<>();

    static {
        MAPPINGS.put(int.class, Integer.class);
    }

    public static Object newProxyInstance(GPClassLoader classLoader, Class<?>[] interfaces, GPInvocationHandler h) {
        try {
            //1、动态生成源代码.java文件
            String src = generateSrc(interfaces);
            System.out.println(src);
            //2、Java文件输出磁盘
            String filePath = GPProxy.class.getResource("").getPath();
            System.out.println(filePath);
            File f = new File(filePath + "$Proxy0.java");
            //文件输出
            try (FileWriter fw = new FileWriter(f)) {
                fw.write(src);
                fw.flush();
            }

            //3、把生成的.java文件编译成.class文件 获得系统编译器
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manage = compiler.getStandardFileManager(null, null, null);
            Iterable<? extends javax.tools.JavaFileObject> iterable = manage.getJavaFileObjects(f);

            JavaCompiler.CompilationTask task = compiler.getTask(null, manage, null, null, null, iterable);
            task.call();
            manage.close();

            //4、编译生成的.class文件加载到JVM中来
            Class<?> proxyClass = classLoader.findClass("$Proxy0");
            java.lang.reflect.Constructor<?> c = proxyClass.getConstructor(GPInvocationHandler.class);
            f.delete();

            //5、返回字节码重组以后的新的代理对象
            return c.newInstance(h);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String generateSrc(Class<?>[] interfaces) {
        StringBuilder sb = new StringBuilder();
        //包
        sb.append("package pattern.proxy.dynamic_proxy.gp_proxy;" + LN);
        //导入
        sb.append("import pattern.proxy.Person;" + LN);
        sb.append("import java.lang.reflect.*;" + LN);
        sb.append("public class $Proxy0 implements ");
        for (Class<?> anInterface : interfaces) sb.append(anInterface.getName());
        sb.append("{").append(LN);
        sb.append("GPInvocationHandler h;" + LN);
        sb.append("public $Proxy0(GPInvocationHandler h) { " + LN);
        sb.append("this.h = h;");
        sb.append("}" + LN);
        //每个接口
        //每个方法
        for (Class<?> anInterface : interfaces)
            for (java.lang.reflect.Method m : anInterface.getMethods()) {
                //每个参数
                Class<?>[] params = m.getParameterTypes();
                //参数名称
                StringBuilder paramNames = new StringBuilder();
                //参数值
                StringBuilder paramValues = new StringBuilder();
                //参数类型
                StringBuilder paramClasses = new StringBuilder();
                for (int i = 0; i < params.length; i++) {
                    Class<?> clazz = params[i];
                    String type = clazz.getName();
                    String paramName = toLowerFirstCase(clazz.getSimpleName());
                    paramNames.append(type).append(" ").append(paramName);
                    paramValues.append(paramName);
                    paramClasses.append(clazz.getName()).append(".class");
                    if (i > 0 && i < params.length - 1) {
                        paramNames.append(",");
                        paramClasses.append(",");
                        paramValues.append(",");
                    }
                }

                sb.append("public ").append(m.getReturnType().getName()).append(" ").append(m.getName()).append("(")
                        .append(paramNames.toString()).append(") {").append(LN);
                sb.append("try{" + LN);
                sb.append("Method m = ").append(interfaces[0].getName()).append(".class.getMethod(\"").append(
                        m.getName()).append("\",new Class[]{").append(paramClasses.toString()).append("});").append(
                        LN);
                sb.append(hasReturnValue(m.getReturnType()) ? "return " : "").append(
                        getCaseCode("this.h.invoke(this,m,new Object[]{" + paramValues + "})", m.getReturnType()))
                        .append(";").append(LN);
                sb.append("}catch(Error _ex) { }");
                sb.append("catch(Throwable e){" + LN);
                sb.append("throw new UndeclaredThrowableException(e);" + LN);
                sb.append("}");
                sb.append(getReturnEmptyCode(m.getReturnType()));
                sb.append("}");
            }
        sb.append("}" + LN);
        return sb.toString();
    }

    private static String getReturnEmptyCode(Class<?> returnClass) {
        if (MAPPINGS.containsKey(returnClass)) return "return 0;";
        else if (returnClass == void.class) return "";
        else return "return null;";
    }

    private static String getCaseCode(String code, Class<?> returnClass) {
        if (MAPPINGS.containsKey(returnClass)) return "((" + MAPPINGS.get(returnClass).getName() + ")" + code + ")."
                + returnClass.getSimpleName()
                + "Value()";
        return code;
    }

    private static boolean hasReturnValue(Class<?> clazz) {
        return clazz != void.class;
    }

    private static String toLowerFirstCase(String src) {
        char[] chars = src.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

}
