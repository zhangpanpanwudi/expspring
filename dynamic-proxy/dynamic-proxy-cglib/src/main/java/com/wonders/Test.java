package com.wonders;

import com.wonders.MethodInterceptor.CglibProxy;
import com.wonders.service.UserService;
import com.wonders.service.impl.UserServiceImpl;
import net.sf.cglib.core.ClassGenerator;
import net.sf.cglib.core.DebuggingClassWriter;

/**
 * @author: zph
 * @data: 2018/12/18 15:11
 */
public class Test {

    public static void main(String[] args) {

        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "E:\\\\wondersgroup\\javaDemo\\com\\wonders");


        UserServiceImpl service = new UserServiceImpl();
        CglibProxy cp = new CglibProxy();
        UserService proxy = (UserService) cp.getProxy(service.getClass());
        proxy.add();
//        proxy.sub();
//        proxy.hello("zlx");
//        proxy.service("zlx");
        proxy.toString();
       // ClassGenerator

    }

}
