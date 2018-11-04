package com.atgugui.result;

import java.io.Serializable;

/**
 * @author dkzadmin
 * 2018-11-2 17:15:49
 */
public class BaseResponse implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1687083375901717817L;
	
	private int status = 200;
    private String message;
    private boolean success =Boolean.TRUE;

    public BaseResponse(int status, String message) {
        this.status = status;
        this.message = message;
        success = false;
    }
    
    

    public BaseResponse() {
		super();
	}



	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
    

}
