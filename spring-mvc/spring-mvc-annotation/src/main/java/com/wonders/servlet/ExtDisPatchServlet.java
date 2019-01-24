package com.wonders.servlet;

import com.sun.net.httpserver.HttpServer;
import com.wonders.annotation.ExtController;
import com.wonders.annotation.ExtRerequestMapping;
import org.apache.commons.lang.StringUtils;
import utils.ClassUtil;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 1、获取包下的所有@controller类
 * 2、遍历所有的类，获取类中方法的访问路径和方法对象，放在map中
 * 3、拦截所有的请求，
 *      3.1、获取请求的地址，根据地址获取类对象和方法对象，
 *      3.2、利用反射执行方法，
 *      3.3、获取到方法执行结果，转发请求。。。
 * @author: zph
 * @data: 2019/01/03 23:20
 */
public class ExtDisPatchServlet extends HttpServlet {
    private static ConcurrentHashMap<String,Object> beanMap = new ConcurrentHashMap<String,Object>();
    private static ConcurrentHashMap<String,Object> urlBeans = new ConcurrentHashMap<String,Object>();
    private static ConcurrentHashMap<String,Method> urlMethrods = new ConcurrentHashMap<String,Method>();

    @Override
    public void init() throws ServletException {
        try {
            //1、扫包，找到所有controller注解
            findController();
            //遍历类集合，找到注解的方法
            findMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        if(StringUtils.isEmpty(requestURI)){
            return;
        }
        Object object = urlBeans.get(requestURI);
        if(object == null){
            resp.getWriter().println("404,not found url");
        }
        Method method = urlMethrods.get(requestURI);

        try {
            //利用反射执行方法。得到String类型的变量
            String invoke = (String) method.invoke(object);
            //渲染视图
            extResourceViewResolver(invoke,req,resp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    private void extResourceViewResolver(String pageName, HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // 根路径
        String prefix = "/";
        String suffix = ".jsp";
        req.getRequestDispatcher(prefix + pageName + suffix).forward(req, res);
    }

    private void findMethod(){
        for (Map.Entry<String, Object> mvcBean : beanMap.entrySet()) {
            StringBuffer url = new StringBuffer();
            Object object = mvcBean.getValue();
            Class<?> classinfo = object.getClass();
            ExtRerequestMapping classinfoAnnotation = classinfo.getAnnotation(ExtRerequestMapping.class);
            if(classinfoAnnotation!=null){
                url.append(classinfoAnnotation.value());
            }
            Method[] methods = classinfo.getMethods();
            for(Method method:methods){
                ExtRerequestMapping methodAnnotation = method.getAnnotation(ExtRerequestMapping.class);
                if(methodAnnotation!=null){
                    url.append(methodAnnotation.value());
                    urlMethrods.put(url.toString(),method);
                    urlBeans.put(url.toString(),object);
                }
            }
        }
    }

    private void findController() throws Exception {
        List<Class<?>> classes = ClassUtil.getClasses("com.wonders.controller");
        for(Class<?> classInfo : classes){
            //判断类上面是否extController注解
            ExtController annotation = classInfo.getAnnotation(ExtController.class);
            if(annotation != null){
                String className =ClassUtil.toLowerCaseFirstOne(classInfo.getName());
                Object instance = ClassUtil.newInstance(classInfo);

                beanMap.put(className,instance);
            }
        }
    }
}
