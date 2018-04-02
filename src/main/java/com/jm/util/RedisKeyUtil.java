package com.jm.util;

/**
 * Created by wukan on 2017-12-16-0016.
 */
public class RedisKeyUtil {
    public static String getUserKey(String str){
        return Constant.USER_LIST_ALL_REDIS_KEY + str;
    }
}
