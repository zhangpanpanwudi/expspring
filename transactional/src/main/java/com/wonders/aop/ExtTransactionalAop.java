package com.wonders.aop;

import com.wonders.annotation.ExtAnnotational;
import com.wonders.utils.TransationUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.lang.reflect.Method;

/**
 * @author: zph
 * @data: 2018/12/09 17:11
 */
@Component
@Aspect
public class ExtTransactionalAop {

    @Autowired
    private TransationUtils transationUtils;

    @Around("execution(* com.wonders.service.*.*(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //1、获取代理对象的方法
        Method method = getMethod(proceedingJoinPoint);
        ExtAnnotational annotation = method.getDeclaredAnnotation(ExtAnnotational.class);
        //2、判断方法是否加注解
        TransactionStatus beggin = beggin(annotation);

        proceedingJoinPoint.proceed();

        //3、结束后提交事务
        if(beggin!=null){
            transationUtils.commit(beggin);
        }

    }

    @AfterThrowing("execution(* com.wonders.service.*.*(..))")
    public void afterThrowing(){
        System.out.println("回滚事务");
        // 获取当前事务 直接回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }

    /**
     * 开启事务
     * @param annotation
     * @return
     */
    public TransactionStatus  beggin(ExtAnnotational annotation){
        if(annotation==null){
            return null;
        }
        return transationUtils.beggin();
    }

    /**
     * 反射获取方法
     * @param proceedingJoinPoint
     * @return
     * @throws NoSuchMethodException
     */
    public Method getMethod(ProceedingJoinPoint proceedingJoinPoint) throws NoSuchMethodException {
        //获取方法名称
        String name = proceedingJoinPoint.getSignature().getName();
        //获取class
        Class<?> classTarget = proceedingJoinPoint.getTarget().getClass();
        //获取方法参数类型
        Class[] parameterTypes = ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterTypes();
        Method method = classTarget.getMethod(name, parameterTypes);
        return method;
    }

}
