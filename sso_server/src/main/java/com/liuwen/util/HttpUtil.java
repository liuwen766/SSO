package com.liuwen.util;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @description: Good good study,day day up!
 * @author: Liu Wen
 * @create: 2020-03-25 14:54
 **/
public class HttpUtil {
    /**
     * @Description:      将params集合转成url，打开url连接成功则返回true？
     * @date 20.3.23 14:54
     */
    //step11
    public static String sendHttpRequest(String httpUrl, Map<String,String> params){
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            if(params!=null && params.size()>0){
                StringBuffer stringBuffer = new StringBuffer();
                for(Map.Entry<String,String> entry:params.entrySet()){
                    stringBuffer.append("&")
                            .append(entry.getKey())
                            .append("=")
                            .append(entry.getValue());
                }
                connection.getOutputStream().write(stringBuffer.substring(1).toString().getBytes("UTF-8"));
            }
            connection.connect();
            String response = StreamUtils.copyToString(connection.getInputStream(), Charset.forName("UTF-8"));
            return response;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
