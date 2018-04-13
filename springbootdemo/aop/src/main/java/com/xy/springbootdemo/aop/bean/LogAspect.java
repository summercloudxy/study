package com.xy.springbootdemo.aop.bean;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {
    @Pointcut("@annotation(com.xy.springbootdemo.aop.bean.Action)")
    public void annotationPointCut(){}
    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Action annotation = method.getAnnotation(Action.class);
        System.out.println("注解式拦截"+annotation.name());
    }

    /**
     * 第一个*表示方法返回值
     * *(..) 表示任意方法，参数任意
     * @param joinPoint
     */
    @Before("execution(* com.xy.springbootdemo.aop.bean.DemoMethodService.*(..))")
    public void before(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println("方法规则式拦截"+method.getName());
    }
}
