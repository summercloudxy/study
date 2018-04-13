package com.xy.springbootdemo.mvc.controller;

import com.xy.springbootdemo.mvc.bean.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MVCController {

    @RequestMapping("test/normal")
    @ResponseBody
    public String normal(@ModelAttribute("author") String author) {
        return author;
    }

    @RequestMapping("test/exception")
    @ResponseBody
    public String exception(@ModelAttribute("author") String author) {
        throw new RuntimeException("test exception");
    }

    /**
     * 调用的时候设置header： Content-Type=application/x-wisely
     * body格式：2-xiayun-25
     * produces = {"application/x-wisely"} 指定返回的媒体类型
     *
     * @param obj
     * @return
     */
    @RequestMapping(value = "test/messageconvert", produces = {"application/x-wisely"})
    @ResponseBody
    public DemoObj exception(@RequestBody DemoObj obj) {
        return obj;
    }


}
