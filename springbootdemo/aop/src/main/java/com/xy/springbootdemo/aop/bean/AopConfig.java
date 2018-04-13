package com.xy.springbootdemo.aop.bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.xy.springbootdemo.aop")
//开启spring对AspectJ的支持
@EnableAspectJAutoProxy
public class AopConfig {

}
