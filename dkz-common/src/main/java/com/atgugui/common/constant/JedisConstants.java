package com.atgugui.common.constant;


/**
 * @author dkzadmin
 *  全局jedis key的配置常量
 */
public class JedisConstants {

    /**2018-9-6 14:38:36 测试缓存基础key
     * 
     */
    public static final String STOCK_PLEDGE_SYSTEM_PARAMS = "STOCK_PLEDGE_SYSTEM_PARAMS";
    
    
    /**2018-11-2 18:17:19 博客首页title
     * 
     */
    public static final String HOME_PAGETA_TITLE = "home_pageta_title";
    
    /**
     * 使用hset ,根据用户找到对应的token
     */
    public static final String SYSTEM_USER_TO_TOKEN = "system_user_to_token";
    
    /**
     * 使用hset ,根据token找到对应的用户
     */
    public static final String SYSTEM_TOKEN_TO_USER = "system_token_to_user";
}
