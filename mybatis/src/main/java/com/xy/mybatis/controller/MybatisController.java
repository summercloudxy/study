package com.xy.mybatis.controller;

import com.github.pagehelper.PageInfo;
import com.xy.mybatis.pojo.TbCard;
import com.xy.mybatis.service.MybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@RestController
public class MybatisController {
    @Autowired
    private MybatisService service;

    @RequestMapping(value = "/cards",method = RequestMethod.GET)
    public PageInfo<TbCard> create(@RequestParam Integer currentPage, @RequestParam Integer pageSize, HttpServletRequest request, HttpServletResponse response){
        PageInfo<TbCard> tbCardPageInfo = service.selectCardByPage(currentPage, pageSize);
        return tbCardPageInfo;
    }

    /**
     * 模拟登陆，利用request.getSession()动作生成一个session
     * request.getSession()补充： 创建或者得到session对象，没有匹配的session编号，会自动创建新的session对象
     * 第一次创建session对象时，给session对象分配一个唯一id-JSESSIONID，把JSESSIONID作为cookie发送给浏览器保存
     * 调用request.getSession()会自动进行该动作：
     *  Cookie cookie = new Cookie("JSESSIONID",session.getId);
     *  response.addCookie(cookie);
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String aa(@RequestParam String userName, HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.setAttribute("username",userName);
        return "success";
    }

}
