package com.xy.springbootdemo.aware;

import com.xy.springbootdemo.aware.bean.AwareService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * bean本身是不知道容器的存在的，Aware是让bean可以获取容器的资源
 */
@SpringBootApplication
public class SpringbootAwareApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringbootAwareApplication.class);
		AwareService bean = context.getBean(AwareService.class);
		bean.outputResult();

//		SpringApplication.run(SpringbootAopApplication.class, args);
	}
}
