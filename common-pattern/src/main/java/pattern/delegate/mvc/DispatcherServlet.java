package pattern.delegate.mvc;

import pattern.delegate.mvc.controller.MemberController;
import pattern.delegate.mvc.controller.OrderController;
import pattern.delegate.mvc.controller.SystemController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 相当于是Leader8的角色
 *
 * @author jill
 */
public class DispatcherServlet extends HttpServlet {

    private final List<Handler> handlerMapping = new ArrayList<>();

    /**
     * 初始化控制台
     *
     * @throws ServletException 异常
     */
    @Override
    public void init() throws ServletException {
        try {
            handlerMapping.add(new Handler()
                                       .setController(MemberController.class.newInstance())
                                       .setMethod(MemberController.class.getMethod("getMemberById", String.class))
                                       .setUrl("/web/getMemberById.json"));
            handlerMapping.add(new Handler()
                                       .setController(OrderController.class.newInstance())
                                       .setMethod(OrderController.class.getMethod("getOrderById", String.class))
                                       .setUrl("/web/getOrderById.json"));
            handlerMapping.add(new Handler()
                                       .setController(SystemController.class.newInstance())
                                       .setMethod(SystemController.class.getMethod("logout"))
                                       .setUrl("/web/logout.json"));
        } catch (Exception ignored) {
            throw new ServletException("fail");
        }
    }

    private void doDispatch(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 1、获取用户请求的url
        //   如果按照J2EE的标准、每个url对对应一个Servlet，url由浏览器输入
        String uri = request.getRequestURI();

        // 2、Servlet拿到url以后，要做权衡（要做判断，要做选择）
        //   根据用户请求的URL，去找到这个url对应的某一个java类的方法
        Handler handle = null;
        for (Handler h : handlerMapping) {
            if (uri.equals(h.getUrl())) {
                handle = h;
                break;
            }
        }
        // 3、通过拿到的URL去handlerMapping（我们把它认为是策略常量）
        Object object = null;
        // 4、将具体的任务分发给Method（通过反射去调用其对应的方法）
        if ("/web/logout.json".equals(uri)) {
            response.getWriter().write("logout");
        } else {
            try {
                assert handle != null;
                //反射调用
                object = handle.getMethod().invoke(handle.getController(), request.getParameter("mid"));
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            // 5、获取到Method执行的结果，通过Response返回出去
            assert object != null;
            response.getWriter().write((char[]) object);
        }
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static class Handler {
        /**
         * 控制台
         */
        private Object controller;
        /**
         * 方法
         */
        private Method method;
        /**
         * url
         */
        private String url;

        public Object getController() {
            return controller;
        }

        public Handler setController(Object controller) {
            this.controller = controller;
            return this;
        }

        public Method getMethod() {
            return method;
        }

        public Handler setMethod(Method method) {
            this.method = method;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public Handler setUrl(String url) {
            this.url = url;
            return this;
        }
    }


}
