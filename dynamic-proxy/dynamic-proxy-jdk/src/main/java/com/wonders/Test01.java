package com.wonders;

import com.wonders.handler.MyInvocationHandler;
import com.wonders.service.UserService;
import com.wonders.service.impl.UserServiceImpl;

/**
 * @author: zph
 * @data: 2018/12/18 11:43
 */
public class Test01 {
    public static void main(String[] args) {
        //生成的代理类保存到磁盘
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        UserService service = new UserServiceImpl();
        MyInvocationHandler handler = new MyInvocationHandler(service);
        UserService proxy = (UserService) handler.getProxy();
        proxy.add();
    }
}
