package com.liuwen.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 * @description: Good good study,day day up!
 * @author: Liu Wen
 * @create: 2020-03-23 11:58
 **/
//step3
public class SSOClientUtils {

    private static Properties properties = new Properties(); //用于加载获取个人配置文件的信息
    public static String CLIENT_HOST_URL;
    public static String SERVER_HOST_URL;

    static {
        try {
            properties.load(SSOClientUtils.class.getClassLoader().getResourceAsStream("sso.properties"));
            SERVER_HOST_URL = properties.getProperty("server.host.url");
            CLIENT_HOST_URL = properties.getProperty("client.host.url");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @Description:
     * @date 20.3.23 12:05
     */
    public static void redirectToCheckToken(HttpServletRequest request, HttpServletResponse response){
        StringBuffer url = new StringBuffer();
        url.append(SERVER_HOST_URL).append("/checkToken?redirectUrl=")
                .append(CLIENT_HOST_URL).append(request.getServletPath());

        try {
            response.sendRedirect(url.toString());  //重定向到服务器（并携带客户端的地址）
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description:   获取ServerLogoutUrl
     * @date 20.3.23 14:57
     */
    //step10
    public static String getServerLogoutUrl(){
        return SERVER_HOST_URL+"/logout";
    }

    /**
     * @Description:     获取ClientLogoutUrl
     * @date 20.3.23 14:57
     */
    //step10
    public static String getClientLogoutUrl(){
        return CLIENT_HOST_URL+"/logout";
    }



}
