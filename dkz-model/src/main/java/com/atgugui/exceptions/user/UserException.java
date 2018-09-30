package com.atgugui.exceptions.user;

import java.io.Serializable;

import com.atgugui.enums.exceptionals.user.UserExceptionEnum;



/**
 * @author dkzadmin
 * 定义用户模块的异常
 * 用户模块异常范围 40000 - 50000
 */
public class UserException  extends RuntimeException implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3850770826090913347L;
	
	/** 用户异常
	 * 
	 */
	private UserExceptionEnum userExceptionEnum;
	
    /**
     * 构造函数
     *
     * @param message 异常消息描述
     * @return BaseException 用户异常类的实例
     */
    public UserException(String message) {
        super(message);
    }

    /**
     * 构造函数
     *
     * @return ApplicationException 用户未登录异常类的实例
     */
    public UserException(UserExceptionEnum userExceptionEnum) {
        super(userExceptionEnum.getMessage());
        this.setuserExceptionEnum(userExceptionEnum);
    }

	public UserExceptionEnum getuserExceptionEnum() {
		return userExceptionEnum;
	}

	public void setuserExceptionEnum(UserExceptionEnum userExceptionEnum) {
		this.userExceptionEnum = userExceptionEnum;
	}
}
