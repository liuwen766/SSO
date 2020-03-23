package com.liuwen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: Good good study,day day up!
 * @author: Liu Wen
 * @create: 2020-03-23 10:43
 **/
@Controller
public class TaobaoHandler {

    //step1:检测启动正确不？
    @RequestMapping("/taobao")
    public String index(){
        return "index";
    }

}
