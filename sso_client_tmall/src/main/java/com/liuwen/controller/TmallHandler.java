package com.liuwen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description: Good good study,day day up!
 * @author: Liu Wen
 * @create: 2020-03-25 11:25
 **/
@Controller
public class TmallHandler {

    @GetMapping("/tmall")
    public String index(Model model){
//        model.addAttribute("serverLogoutUrl", SSOClientUtil.getServerLogoutUrl());
        return "index";
    }
}
