package com.atgugui.exceptions;

import java.io.Serializable;

import com.atgugui.enums.exceptionals.StateEnum;



/**
 * @author dkzadmin
 * 自定义全局异常抛出,逻辑处理层使用
 */
public class BaseException  extends RuntimeException implements Serializable{
	private static final long serialVersionUID = -4333316296251054416L;
	private StateEnum stateEnum;

    /**
     * 构造函数
     *
     * @param message 异常消息描述
     * @return BaseException 用户异常类的实例
     */
    public BaseException(String message) {
        super(message);
    }

    /**
     * 构造函数
     *
     * @return ApplicationException 用户未登录异常类的实例
     */
    public BaseException(StateEnum stateEnum) {
        super(stateEnum.getMessage());
        this.setStateEnum(stateEnum);
    }

	public StateEnum getStateEnum() {
		return stateEnum;
	}

	public void setStateEnum(StateEnum stateEnum) {
		this.stateEnum = stateEnum;
	}
}
