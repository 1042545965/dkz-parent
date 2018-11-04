package com.atgugui.result;


/**
 * Created by Ace on 2017/6/11.
 */
public class RestResponse<T> extends BaseResponse {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5675035663269304863L;
	
	T resultData;

	public T getResultData() {
		return resultData;
	}

	public void setResultData(T resultData) {
		this.resultData = resultData;
	}

	public RestResponse(int status, String message, T resultData) {
		super(status, message);
		this.resultData = resultData;
	}

	public RestResponse(T resultData) {
		this.resultData = resultData;
	}

}
