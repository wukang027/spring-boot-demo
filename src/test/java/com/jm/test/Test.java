package com.jm.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jm.domain.User;

public class Test {
//    public static void main(String[] args){
//        User user = new User();
//        user.setUserName("tom");
//        user.setUserId(26);
//        System.out.println(JSONObject.toJSONString(user));
//        System.out.println(JSON.toJSONString(user,true));
//    }
    @org.junit.Test
    public void test(){
        User user = new User();
        user.setUserName("tom");
        user.setUserId(26);
        System.out.println(JSONObject.toJSONString(user));
        System.out.println(JSON.toJSONString(user,false));
    }
}
