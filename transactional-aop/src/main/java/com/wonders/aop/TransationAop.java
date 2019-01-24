package com.wonders.aop;

import com.wonders.transtion.TransationUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author: zph
 * @data: 2018/12/09 15:15
 */
@Component
@Aspect
public class TransationAop {

    @Autowired
    private TransationUtils transationUtils;

    /**
     * 环绕通知管理事务
     * @param proceedingJoinPoint
     * @throws Throwable
     */
    @Around("execution(* com.wonders.service.UserService.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("开启事务");
        TransactionStatus transactionStatus = transationUtils.beggin();
        proceedingJoinPoint.proceed();
        System.out.println("提交事务");
        transationUtils.commit(transactionStatus);
    }

    @AfterThrowing("execution(* com.wonders.service.UserService.add(..))")
    public void afterThrowing(){
        System.out.println("回滚事务");
        // 获取当前事务 直接回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
}
