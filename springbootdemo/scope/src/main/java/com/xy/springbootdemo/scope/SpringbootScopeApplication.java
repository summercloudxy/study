package com.xy.springbootdemo.scope;

import com.xy.springbootdemo.scope.bean.PrototypeService;
import com.xy.springbootdemo.scope.bean.ScopeConfig;
import com.xy.springbootdemo.scope.bean.SingletonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringbootScopeApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(SpringbootScopeApplication.class, args);

		//scope为singleton，全局共享一个实例
		SingletonService s1 = context.getBean(SingletonService.class);
		SingletonService s2 = context.getBean(SingletonService.class);
		//scope为prototype，每次调用新建一个实例
		PrototypeService p1 = context.getBean(PrototypeService.class);
		PrototypeService p2 = context.getBean(PrototypeService.class);
		System.out.println(s1==s2);
		System.out.println(p1==p2);
	}
}
