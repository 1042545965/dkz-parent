package com.atgugui.result;



import java.io.Serializable;

import com.atgugui.enums.exceptionals.StateEnum;

/**
 * @author <a href='mailto:bovenjoy@163.com'>周志豪</a>.
 * @version createTime:2017/5/4 14:48 bovenjoy
 */
public class BaseResult implements Serializable {
    private static final long serialVersionUID = 5526838292297786283L;
    private Integer code = StateEnum.SUCCESS_OPTION.getCode();
    private Boolean success = Boolean.TRUE;
    private String resultMsg = StateEnum.SUCCESS_OPTION.getMessage();
    private Object resultData;

    public BaseResult() {
    }

    public static BaseResult newFailed(StateEnum stateEnum) {
        BaseResult result = new BaseResult();
        result.setSuccess(Boolean.FALSE);
        result.setCode(stateEnum.getCode());
        result.setResultMsg(stateEnum.getMessage());
        return result;
    }

    public static BaseResult newFailed(String resultMsg) {
        BaseResult result = new BaseResult();
        result.setSuccess(Boolean.FALSE);
        result.setCode(StateEnum.ERROR_SYSTEM.getCode());
        result.setResultMsg(resultMsg);
        return result;
    }
    
    public static BaseResult newFailed(Integer code , String resultMsg) {
        BaseResult result = new BaseResult();
        result.setSuccess(Boolean.FALSE);
        result.setCode(code);
        result.setResultMsg(resultMsg);
        return result;
    }

    public static BaseResult newSuccess(StateEnum stateEnum) {
        BaseResult result = new BaseResult();
        result.setSuccess(Boolean.TRUE);
        if (stateEnum == null) {
            result.setCode(StateEnum.SUCCESS_OPTION.getCode());
            return result;
        }
        result.setCode(stateEnum.getCode());
        result.setResultMsg(stateEnum.getMessage());
        return result;
    }



    public static BaseResult newSuccess() {
        return new BaseResult();
    }

    public static BaseResult newSuccess(Object data) {
        BaseResult result = new BaseResult();
        result.setSuccess(Boolean.TRUE);
        result.setCode(StateEnum.SUCCESS_OPTION.getCode());
        result.setResultData(data);
        return result;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }


    public String getResultMsg() {
        return this.resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

	public Object getResultData() {
		return resultData;
	}

	public void setResultData(Object resultData) {
		this.resultData = resultData;
	}
}
