package com.wonders.hotswap;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: zph
 * @data: 2018/12/09 11:53
 */
public class HotSwap {

    public void loadUser() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassloader classloader = new MyClassloader();
        Class<?> aClass = classloader.findClass("com.wonders.User");
        Object ob = aClass.newInstance();
        Method add = aClass.getMethod("add");
        add.invoke(ob);
    }
}
