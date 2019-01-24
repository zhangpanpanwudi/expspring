package com.wonders.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author: zph
 * @data: 2018/12/09 21:26
 */
public class MyProcessor implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("实现了BeanPostProcessor接口，回调postProcessBeforeInitialization()方法,o:" + o + ",s:" + s);
        return o;
    }

    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("实现了BeanPostProcessor接口，回调postProcessAfterInitialization()方法,o:" + o + ",s:" + s);
        return o;
    }
}
