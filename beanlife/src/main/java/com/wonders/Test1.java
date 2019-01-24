package com.wonders;

import com.wonders.service.HelloService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: zph
 * @data: 2018/12/09 20:21
 */
public class Test1 {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        HelloService helloService = (HelloService) applicationContext.getBean("helloService");
        helloService.hello();
        System.out.println("关闭容器");
        ((ClassPathXmlApplicationContext)applicationContext).close();
        System.out.println("关闭完成");
    }


}
