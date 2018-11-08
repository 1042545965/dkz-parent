package com.atgugui.enums.exceptionals.user;

import java.util.HashMap;
import java.util.Map;


/**
 * @author dkzadmin
 * 全局基础异常的枚举
 * 用户模块异常范围 40000 - 50000
 */
public enum UserExceptionEnum {
	//用户登录模块异常
	ERROR_USER_LOGIN_PASSWORD_LENGTH(40000, "密码长度异常"),
	ERROR_USER_LOGIN_USERNAME_NONE(40001, "该用户不存在"),
	ERROR_USER_LOGIN_USERNAME_AND_PASSWORD_NONE(40002, "用户名或密码为空"),
	ERROR_USER_LOGIN_USERNAME_IS_DELETE(40003, "该用户已被删除"),
	ERROR_USER_LOGIN_USERNAME_STOP_USE(40004, "该用户已被停用"),
	ERROR_USER_LOGIN_USERNAME_PASS_NUMBER(40005, "密码错误超过规定次数"),
	ERROR_USER_LOGIN_PASSWORD_ERROR(40006, "密码输入错误"),
    
	//用户注册模块异常
	ERROR_USER_REGISTER_PHONE_NUMBER_ERROR(50001, "手机号码格式错误"),
	ERROR_USER_REGISTER_PASSWORD_NOT_MATCH_ERROR(50002, "密码格式错误"),
	ERROR_USER_REGISTER_PHONE_REPEAT_ERROR(50003, "号码已被注册"),
	ERROR_USER_REGISTER_EMAIL_ERROR(50004, "email格式错误"),
	ERROR_USER_REGISTER_EMAIL_BE_REGISTER_ERROR(50005, "email重复"),
	
	//验证码异常
	ERROR_USER_REGISTER_VALIDATE_CODE_LOSS_ERROR(60000, "验证码已失效"),
	ERROR_USER_REGISTER_VALIDATE_INTERVAL_TIME_ERROR(60001, "请勿频繁发送验证码");

    private Integer code;

    private String message;

    private UserExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     *将该枚举全部转化成map 对象
     * @return
     */
    public static  Map<Integer, String> getMap(){
        Map<Integer, String> map = new HashMap<Integer, String>();
        for (UserExceptionEnum e : UserExceptionEnum.values()) {
        	map.put(e.getCode(), e.getMessage());
        }
        return map;
    }
}
