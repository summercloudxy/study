package com.xy.springbootdemo.aop;

import com.xy.springbootdemo.aop.bean.AopConfig;
import com.xy.springbootdemo.aop.bean.DemoAnnotationService;
import com.xy.springbootdemo.aop.bean.DemoMethodService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringbootAopApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
		//两种方式实现aop
		DemoAnnotationService annotationBean = context.getBean(DemoAnnotationService.class);
		DemoMethodService methodBean = context.getBean(DemoMethodService.class);
		annotationBean.add();
		methodBean.add();


//		SpringApplication.run(SpringbootAopApplication.class, args);
	}
}
