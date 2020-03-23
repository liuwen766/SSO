package com.liuwen.db;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @description: Good good study,day day up!
 * @author: Liu Wen
 * @create: 2020-03-23 13:09
 **/
//step7
public class MockDB {

    //记录token  (set集合，去重)
    public static Set<String> tokenSet = new HashSet<>();

    //客户端登出地址
    public static Map<String,Set<String>> clientLogoutUrlMap = new HashMap<>();

}
