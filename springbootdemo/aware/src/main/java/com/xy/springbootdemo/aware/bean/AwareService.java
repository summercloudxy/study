package com.xy.springbootdemo.aware.bean;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AwareService implements BeanNameAware,ResourceLoaderAware {
    private String beanName;
    private ResourceLoader resourceLoader;


    /**
     * 实现BeanNameAware接口，得到容器中Bean的名称
     * @param s
     */
    @Override
    public void setBeanName(String s) {
        this.beanName = s;
    }

    /**
     * 获取资源加载器
     * @param resourceLoader
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void outputResult(){
        System.out.println(beanName);
        Resource resource = resourceLoader.getResource("classpath:awaretest");
        try {
            System.out.println(resource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
