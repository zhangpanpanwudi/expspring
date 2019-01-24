package com.wonders.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author: zph
 * @data: 2018/12/09 20:22
 */
@Component
public class HelloService implements BeanNameAware, BeanFactoryAware, ApplicationContextAware,
        InitializingBean, DisposableBean {

    private String name;

    public HelloService() {
        System.out.println("HelloService的无参构造函数");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("set方法被调用");
    }

    public void hello() {
        System.out.println("hello:" + name);
    }

    public void setBeanName(String s) {
        System.out.println("实现了BeamNameAware接口，回调setBeanName方法获取到自己在配置文件中的id:" + s);
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("实现BeanFactoryAware接口,回调setBeanFactory获取到自己所在的BeanFactory");
    }


    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("实现了ApplicationContextAware接口,回调setApplicationContext获取到自己所在的" +
                "ApplicationContext上下文");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("实现了InitializingBean接口,回调afterPropertiesSet()方法");
    }

    public void myInit() {
        System.out.println("配置了自定义初始化方法,调用自定义初始化方法。。。");
    }


    public void destroy() throws Exception {
        System.out.println("实现了DisposableBean接口,回调destroy()方法");
    }

    public void myDestroy() {
        System.out.println("配置了自定义销毁方法，调用自定义销毁");
    }
}
