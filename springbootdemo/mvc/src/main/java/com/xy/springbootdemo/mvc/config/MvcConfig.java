package com.xy.springbootdemo.mvc.config;

import com.xy.springbootdemo.mvc.bean.DemoInterceptor;
import com.xy.springbootdemo.mvc.bean.DemoMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

//开启spring mvc的配置，完全替代spring boot的默认配置
//@EnableWebMvc
@Configuration
public class MvcConfig implements WebMvcConfigurer{
    /**
     * 静态资源映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将所有/product/images/** 访问都映射到classpath:/product/images/ 目录下
        registry.addResourceHandler("/product/images/**").addResourceLocations("classpath:/product/images/");
        registry.addResourceHandler("/files/**").addResourceLocations("file:D:/test/");
    }


    /**
     * 在容器中放入一个拦截器的bean
     * @return
     */
    @Bean
    public DemoInterceptor demoInterceptor(){
        return new DemoInterceptor();
    }

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoInterceptor());
    }

    /**
     * 在容器中放入一个消息转换器的bean
     * @return
     */
    @Bean
    public DemoMessageConverter demoMessageConverter(){
        return new DemoMessageConverter();
    }


    /**
     * 注册消息转换器
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(demoMessageConverter());
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }
}
