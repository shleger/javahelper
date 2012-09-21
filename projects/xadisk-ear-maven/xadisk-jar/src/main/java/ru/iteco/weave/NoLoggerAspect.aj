package ru.iteco.weave;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: shleger
 * Date: 20.09.12
 * Time: 18:00
 * To change this template use File | Settings | File Templates.
 */
@Aspect
public class NoLoggerAspect {

    @Before("execution(* ru.iteco.foraspecting.TestWeave.getAs())")
    public void processEncodeResponseForFailure(JoinPoint joinPoint) {
        System.out.println("Print aspected!"); }

//    @Pointcut("call(ru.iteco.foraspecting.TestWeave.new(..))")
//    public static void init(ProceedingJoinPoint pjp) {
//    }
//
//    @Around("init(pjp)")
//    public Object initAdvice(ProceedingJoinPoint pjp) throws Throwable{
//        Object ret = pjp.proceed();
//        return new Object();
//    }


}
