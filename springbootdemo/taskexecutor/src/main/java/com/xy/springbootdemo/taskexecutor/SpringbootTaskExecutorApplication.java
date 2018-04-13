package com.xy.springbootdemo.taskexecutor;

import com.xy.springbootdemo.taskexecutor.bean.AsyncTaskService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootTaskExecutorApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(SpringbootTaskExecutorApplication.class, args);
        AsyncTaskService taskService = context.getBean(AsyncTaskService.class);
        for (int i = 0; i < 10; i++) {
            //这两个方法的调用不是顺序执行的
            taskService.executeAsyncTask(i);
            taskService.executeAsyncTaskPlus(i);
        }
    }
}
