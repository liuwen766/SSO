package com.liuwen.config;

import com.liuwen.interceptor.TaobaoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @description:     拦截器的配置
 * @author: Liu Wen       （在SpringBoot中需要写个配置类，相当于Java web的@WebFilter("/taobao")）
 * @create: 2020-03-23 12:21
 **/
//step4
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] patterns = {"/taobao"};                //相当于@WebFilter("/taobao")
        registry.addInterceptor(new TaobaoInterceptor()).addPathPatterns(patterns);
        // 对 TaobaoInterceptor 拦截的请求进行定义
    }
}
