package com.xy.springbootdemo.mvc.bean;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 实现HandlerInterceptor或继承HandlerInterceptorAdapter实现自定义拦截器
 * 在WebMvcAdaptor中重写addInterceptor注册拦截器
 */
public class DemoInterceptor extends HandlerInterceptorAdapter {
    /**
     * 发生请求前执行
     * 返回为false 时，表示请求结束，后续的Interceptor 和Controller 都不会再执行
     * Interceptor 是链式的调用的，在一个应用中或者说是在一个请求中可以同时存在多个Interceptor，每个Interceptor 的调用会依据它的声明顺序依次执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime",startTime);
        return true;
    }

    /**
     * 请求完成后执行
     * Controller 方法调用之后执行，但是它会在DispatcherServlet 进行视图渲染之前被调用，所以我们可以在这个方法中对Controller 处理之后的ModelAndView 对象进行操作
     * postHandle 方法被调用的方向跟preHandle 是相反的，也就是说先声明的Interceptor 的postHandle 方法反而会后执行
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (Long)request.getAttribute("startTime");
        request.removeAttribute("startTime");
        long endTime = System.currentTimeMillis();
        System.out.println("本次请求处理时间为："+(endTime-startTime)+"ms");
        request.setAttribute("handlingTime",endTime-startTime);
    }


    /**
     * 会在整个请求处理完成，也就是在视图返回并被渲染之后执行
     * 在该方法中可以进行资源的释放操作
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
