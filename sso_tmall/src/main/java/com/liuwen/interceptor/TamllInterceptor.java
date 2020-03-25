package com.liuwen.interceptor;

import com.liuwen.util.HttpUtil;
import com.liuwen.util.SSOClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @description: Good good study,day day up!
 * @author: Liu Wen
 * @create: 2020-03-23 11:43
 **/
//step2:拦截请求，检验token
@Slf4j       //日志输出（这是常用的日志，要学习一下）
public class TamllInterceptor implements HandlerInterceptor {
    /**
     * @Description:      这里用的是SpringBoot封装好的拦截器：HandlerInterceptor
     * @date 20.3.23 11:44                （相当于java web中的拦截器filter）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1、判断是否已经登录   (true:放行  false:不放行)
        HttpSession session = request.getSession();
        Boolean isLogin = (Boolean)session.getAttribute("isLogin");
        if(isLogin!=null && isLogin){
            return true;
        }
        //2、判断token(第二步不是走else(isLogin==false)路径，而是检验token)
        String token =request.getParameter("token");
        //step8
        if(!StringUtils.isEmpty(token)){       //工具类判断是否为空
            //验证token
            log.info("token存在，需要验证");
            //发起验证      走step9
            String httpUrl = SSOClientUtils.SERVER_HOST_URL+"/verify";
            HashMap<String, String> params =new HashMap<>();
            params.put("token",token);
            params.put("clientLogoutUrl",SSOClientUtils.getClientLogoutUrl());   //走step10
            //将params集合转成url
            String isVerify = HttpUtil.sendHttpRequest(httpUrl,params);          //走step11
            if("true".equals(isVerify)){
                log.info("token验证通过，token={}",token);
                //token保存到本地Cookie
                Cookie cookie = new Cookie("token",token);
                //通过response将token保存到本地Cookie
                response.addCookie(cookie);
                session.setAttribute("isLogin",true);
                return true;
            }
        }
        //step3
        //3、跳转到认证中心进行登录（1、2执行完后没有返回值，走3、认证中心进行登录（工具类实现））
        SSOClientUtils.redirectToCheckToken(request,response);     //走服务器认证step5
        return false;       // 走到这里，所以是未登录状态，所以返回false
    }
}
