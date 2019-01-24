package com.wonders.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author: zph
 * @data: 2018/12/09 15:00
 */
@Aspect
@Component
public class LogAop {

  /*  //前置通知、后置通知、环绕通知、运行通知，异常通知
    @Before("execution(* com.wonders.service.UserService.add(..))")
    public void bofore(){
        System.out.println("前置通知！");
    }

    @After("execution(* com.wonders.service.UserService.add(..))")
    public void after(){
        System.out.println("后置通知");
    }

    @AfterReturning("execution(* com.wonders.service.UserService.add(..))")
    public void afterReturning(){
        System.out.println("运行通知！");
    }

    @AfterThrowing("execution(* com.wonders.service.UserService.add(..))")
    public void afterthrowing(){
        System.out.println("异常通知");
    }

    @Around("execution(* com.wonders.service.UserService.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕通知前");
        proceedingJoinPoint.proceed();
        System.out.println("环绕通知前后");
    }*/
}
