package com.wonders.spring;

import com.wonders.annotation.ExpService;
import com.wonders.utils.ClassUtil;
import org.apache.commons.lang.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: zph
 * @data: 2018/12/14 18:33
 */
public class AnnotationApplicationContext {

    // xml路径地址
    private String packageName;
    ConcurrentHashMap<String, Object> initBean = null;

    public AnnotationApplicationContext(String packageName) throws Exception {
        this.packageName = packageName;
        initBean();
        for(Object object:initBean.values()){
            //4、注入属性(依赖注入)
            attriAssign(object);
        }

    }

    public Object getBean(String beanid) throws Exception {

       if(StringUtils.isEmpty(beanid)){
           throw new Exception("beanid不能为空！");
       }
        //3、使用beanid查找对应的bean对象
        Object object = initBean.get(beanid);

        return object;
    }

    private void attriAssign(Object object) throws IllegalAccessException {
        //1、获取类的所有属性
        //2、遍历属性，
        Class<?> aClass = object.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            //判断是否有注解。。。
            ExpService annotation = field.getAnnotation(ExpService.class);
            if(annotation !=null){
                String name = field.getName();
                Object obj = initBean.get(name);
                if (obj != null) {
                    field.setAccessible(true);
                    field.set(object, obj);
                }
            }
        }
    }

    //2、使用反射创建对象
    private ConcurrentHashMap<String, Object> initBean() throws Exception {
        List<Class<?>> classExpService = findClassExpService();
        if (classExpService == null || classExpService.isEmpty()) {
            throw new Exception("没有需要初始化的bean");
        }
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap<String, Object>();
        for (Class classInfo : classExpService) {
            Object obj = classInfo.newInstance();
            String beanId = toLowerCaseFirstOne(classInfo.getSimpleName());
            concurrentHashMap.put(beanId, obj);
        }
        return concurrentHashMap;
    }


    //查找包下所有注解类//1、读取配置文件
    public List<Class<?>> findClassExpService() throws Exception {
        if (StringUtils.isEmpty(packageName)) {
            throw new Exception("扫包地址不能为空！");
        }
        List<Class<?>> classes = ClassUtil.getClasses(packageName);
        List<Class<?>> existCLassAnnotation = new ArrayList<Class<?>>();
        for (Class classInfo : classes) {
            Annotation annotation = classInfo.getDeclaredAnnotation(ExpService.class);
            if (annotation != null) {
                existCLassAnnotation.add(classInfo);
            }
        }
        return existCLassAnnotation;

    }

    // 首字母转小写
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
}
