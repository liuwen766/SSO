package com.liuwen.controller;

import com.liuwen.util.SSOClientUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @description: Good good study,day day up!
 * @author: Liu Wen
 * @create: 2020-03-25 11:25
 **/
@Controller
public class TmallHandler {

    @GetMapping("/tmall")
    public String index(Model model){
        model.addAttribute("serverLogoutUrl", SSOClientUtils.getServerLogoutUrl());
        return "index";
    }

    //step15:开始走单点登出业务（到服务器，将session的token删除）
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/tmall";
    }
}
