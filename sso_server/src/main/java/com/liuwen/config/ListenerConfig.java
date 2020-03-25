package com.liuwen.config;

import com.liuwen.listener.SessionListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//step18：监听器的配置
@Configuration
public class ListenerConfig implements WebMvcConfigurer {

    @Bean
    public ServletListenerRegistrationBean bean(){
        ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean();
        bean.setListener(new SessionListener());
        return bean;
    }

}
