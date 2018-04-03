package com.dong.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
    @Pointcut("execution(* com.dong.service.*.*(..))")
    public void pointcut(){

    }
    @Before("pointcut()")
    public void beforeAdvice(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName+"before advice...");
    }
    @AfterReturning(value = "pointcut()",returning = "result")
    public  void AfterReturning(JoinPoint joinPoint,Object result){
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName+"afterReturning advice ..");
    }
    @AfterThrowing(value = "pointcut()",throwing = "e")
    public  void afterThrowing(JoinPoint joinPoint,Exception e){
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName + " after excption");
    }
    @After("pointcut()")
    public void after(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName + " after Advice");
    }
    @Around("pointcut()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint){
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
}
