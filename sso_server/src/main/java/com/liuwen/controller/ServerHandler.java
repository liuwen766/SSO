package com.liuwen.controller;

import com.liuwen.db.MockDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @description: Good good study,day day up!
 * @author: Liu Wen
 * @create: 2020-03-23 12:38
 **/
@Controller
@Slf4j
public class ServerHandler {
    /**
     * @Description:     第一次登陆验证token
     * @date 20.3.23 12:39
     */
    //step5
    @RequestMapping("/checkToken")
    public String checkToken(String redirectUrl, HttpSession session, Model model, HttpServletRequest request){
        //获取token
        String token = (String) session.getServletContext().getAttribute("token");
        if(StringUtils.isEmpty(token)){
            model.addAttribute("redirectUrl",redirectUrl);
            return "login";          //token为null，说明是第一次登陆验证。
        }else{
            //验证token
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie:cookies) {
                if(cookie.getValue().equals(token)){  //验证token是否相等
                    //验证通过，返回客户端
                    log.info("token验证通过");
                    return "redirect:"+redirectUrl+"?token="+token;
                }
            }
        }
        model.addAttribute("redirectUrl",redirectUrl);
        return "login";   //验证不通过，则直接返回登录页面。走step6
    }
    /**
     * @Description:   登陆验证
     * @date 20.3.23 13:02
     */
    //step6
    @PostMapping("/login")
    public String login(String username, String password, HttpSession session, String redirectUrl, Model model){
        //这里为了简便，直接给数据验证，就不连数据库查表了
        if(username.equals("admin")&&password.equals("123123")){
            //1、登陆成功，创建token   （创建token的算法有很多种，去了解一下）
            String token = UUID.randomUUID().toString();
            log.info("token创建成功！token={}",token);
            //2、token 保存到全局会话中
            session.getServletContext().setAttribute("token",token);
            //3、token 保存到数据库 (mock)         走step7
            MockDB.tokenSet.add(token);
            //4、返回客户端
            return "redirect:"+redirectUrl+"?token="+token;           //走step8
        }else {
            //登陆失败，用户名或者密码错误
            log.error("用户名密码错误！username={},password={}",username,password);
            model.addAttribute("redirectUrl",redirectUrl);
            return "login";
        }
    }


    /**
     * @Description:      登出验证
     * @date 20.3.23 14:23
     */
    //step9
    @RequestMapping("/verify")
    @ResponseBody
    public String verifyToken(String token,String clientLogoutUrl) {
        if(MockDB.tokenSet.contains(token)){
            Set<String> set =MockDB.clientLogoutUrlMap.get(token);
            if(set == null){
                set = new HashSet<>();
            }
            set.add(clientLogoutUrl);
            MockDB.clientLogoutUrlMap.put(token,set);
            return "true";
        }
        return "false";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }



}
