package com.xy.springbootdemo.aop.bean;

import org.springframework.stereotype.Service;

@Service
public class DemoAnnotationService {
    @Action(name = "注解式拦截")
    public void add(){}
}
