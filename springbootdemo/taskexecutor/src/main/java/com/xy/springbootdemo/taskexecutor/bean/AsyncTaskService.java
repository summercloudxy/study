package com.xy.springbootdemo.taskexecutor.bean;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncTaskService {


    /**
     * @Async表明该方法通过线程池异步执行，如果注解在类上，表明该类所有方法都是异步方法
     * @param i
     */
    @Async
    public void executeAsyncTask(Integer i){
        System.out.println("执行异步任务："+i);
    }


    public void executeAsyncTaskPlus(Integer i){
        System.out.println("执行异步任务+1："+i);
    }

}
