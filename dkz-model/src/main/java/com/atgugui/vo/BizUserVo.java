package com.atgugui.vo;

import java.io.Serializable;

import com.atgugui.model.user.BizUser;

/**
 * @author dkzadmin
 * 返回前台对象
 */
public class BizUserVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BizUser bizUser;
	
	private String token;

	public BizUser getBizUser() {
		return bizUser;
	}

	public void setBizUser(BizUser bizUser) {
		this.bizUser = bizUser;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
