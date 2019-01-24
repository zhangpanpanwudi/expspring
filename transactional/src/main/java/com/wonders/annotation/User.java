package com.wonders.annotation;

import java.lang.reflect.Method;

/**
 * 自定义注解测试类
 *
 * @author: zph
 * @data: 2018/12/09 16:34
 */
public class User {

    @AddAnnotation(userId = 1,userName = "张三",arrays = {"1"})
    public void add() {

    }

    public void delete(){

    }

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> forName = Class.forName("com.wonders.annotation.User");
        Method[] methods = forName.getDeclaredMethods();
        for (Method method:methods){
            System.out.println("方法名称:"+method.getName());
            AddAnnotation declaredAnnotation = method.getDeclaredAnnotation(AddAnnotation.class);
            if(declaredAnnotation==null){
                System.out.println("该方法没有加注解！");
                continue;
            }else{
                System.out.println("userid:"+declaredAnnotation.userId());
                System.out.println("username:"+declaredAnnotation.userName());
                System.out.println("arrays:"+declaredAnnotation.arrays());
            }

        }
    }
}
