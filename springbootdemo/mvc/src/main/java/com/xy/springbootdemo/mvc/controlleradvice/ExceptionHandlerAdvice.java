package com.xy.springbootdemo.mvc.controlleradvice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 * 声明一个控制器建言，可以用于定义@ExceptionHandler、@InitBinder、@ModelAttribute，并应用到所有@RequestMapping中，进行全局处理
 */
@ControllerAdvice
public class ExceptionHandlerAdvice{

    /**
     * 全局处理控制器内的异常
     * 可自定义拦截的异常，这里拦截Exception.class即所有的异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String exception(Exception exception, WebRequest request){
        return exception.getMessage();
    }

    /**
     * 添加键值对到model中，可以让全局的@RequestMapping都能获得在此处设置的键值对
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("author","xiayun");
    }


}
