package com.dong.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Advice {
    //前置通知
    public void beforeAdvice(JoinPoint joinPoint){
        //方法名称
        String methodName = joinPoint.getSignature().getName();
        //方法的参数列表
        Object[] args = joinPoint.getArgs();
        System.out.println(methodName+"-->前置通知"+ Arrays.asList(args));
    }
    public void afterAdvice(Object result){
        System.out.println("后置通知：" + result);
    }
    public  void  exceptionAdvice(Exception e){
        System.out.println("异常通知：" + e.getMessage());
    }
    public  void finnallyAdvice(){
        System.out.println("最终通知");
    }
    public Object aroundAdcice(ProceedingJoinPoint joinPoin) {
        Object result = null;
        try {
            System.out.println("------");
            result = joinPoin.proceed();
            System.out.println("*****");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("++++++");
        } finally {
            System.out.println("&&&&&&");
        }
        return result;
    }
}
