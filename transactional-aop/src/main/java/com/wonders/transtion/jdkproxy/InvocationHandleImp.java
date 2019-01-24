package com.wonders.transtion.jdkproxy;

import com.wonders.service.UserService;
import com.wonders.service.impl.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk反射动态代理
 * @author: zph
 * @data: 2018/12/09 13:56
 */
public class InvocationHandleImp implements InvocationHandler {

    private Object target;

    public InvocationHandleImp(Object target){
        this.target=target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("方法调用前。。。。。。。。。");
        result = method.invoke(target,args);
        System.out.println("方法调用后。。。。。。。。。");
        return result;

    }

    public static void main(String[] args) {
        //必须要用接口
        UserService userService = new UserServiceImpl();
        InvocationHandleImp invocationHandleImp = new InvocationHandleImp(userService);
        ClassLoader classLoader = userService.getClass().getClassLoader();
        Class<?>[] interfaces = userService.getClass().getInterfaces();
        UserService newProxyInstance = (UserService) Proxy.newProxyInstance(classLoader, interfaces, invocationHandleImp);
        newProxyInstance.add();
    }
}
