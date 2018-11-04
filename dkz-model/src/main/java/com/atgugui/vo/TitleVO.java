package com.atgugui.vo;

import java.io.Serializable;

/**
 * @author dkzadmin
 * 首页title 的返回对象
 */
public class TitleVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -987600762212565294L;
	
	private String titelName;
	
	private String titleRoute;

	public String getTitelName() {
		return titelName;
	}

	public void setTitelName(String titelName) {
		this.titelName = titelName;
	}

	public String getTitleRoute() {
		return titleRoute;
	}

	public void setTitleRoute(String titleRoute) {
		this.titleRoute = titleRoute;
	}
	
	
	

}
