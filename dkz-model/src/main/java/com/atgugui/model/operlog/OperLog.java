package com.atgugui.model.operlog;

import java.util.Date;

import com.atgugui.model.BaseEntity;

/**
 * 操作日志记录 oper_log
 * 
 * @author ruoyi
 */
public class OperLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 日志主键 */
    private Long operId;

    /** 操作模块 */
    private String title;

    /** 操作业务类型 */
    private Integer businessType;

    /** 请求方法 */
    private String method;

    /** 操作人类别 */
    private Integer operatorType;

    /** 操作人员 */
    private String operName;

    /** 部门名称 */
    private String deptName;

    /** 请求url */
    private String operUrl;

    /** 操作地址 */
    private String operIp;

    /** 操作地点 */
    private String operLocation;

    /** 请求参数 */
    private String operParam;

    /** 状态0正常 1异常 */
    private Integer status;

    /** 错误消息 */
    private String errorMsg;

    /** 操作时间 */
    private Date operTime;

    public Long getOperId()
    {
        return operId;
    }

    public void setOperId(Long operId)
    {
        this.operId = operId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Integer getBusinessType()
    {
        return businessType;
    }

    public void setBusinessType(Integer businessType)
    {
        this.businessType = businessType;
    }

    public String getMethod()
    {
        return method;
    }

    public void setMethod(String method)
    {
        this.method = method;
    }

    public Integer getOperatorType()
    {
        return operatorType;
    }

    public void setOperatorType(Integer operatorType)
    {
        this.operatorType = operatorType;
    }

    public String getOperName()
    {
        return operName;
    }

    public void setOperName(String operName)
    {
        this.operName = operName;
    }

    public String getDeptName()
    {
        return deptName;
    }

    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }

    public String getOperUrl()
    {
        return operUrl;
    }

    public void setOperUrl(String operUrl)
    {
        this.operUrl = operUrl;
    }

    public String getOperIp()
    {
        return operIp;
    }

    public void setOperIp(String operIp)
    {
        this.operIp = operIp;
    }

    public String getOperLocation()
    {
        return operLocation;
    }

    public void setOperLocation(String operLocation)
    {
        this.operLocation = operLocation;
    }

    public String getOperParam()
    {
        return operParam;
    }

    public void setOperParam(String operParam)
    {
        this.operParam = operParam;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public String getErrorMsg()
    {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }

    public Date getOperTime()
    {
        return operTime;
    }

    public void setOperTime(Date operTime)
    {
        this.operTime = operTime;
    }

    @Override
    public String toString()
    {
        return "OperLog [operId=" + operId + ", title=" + title + ", businessType=" + businessType + ", method="
                + method + ", operatorType=" + operatorType + ", operName=" + operName + ", deptName=" + deptName
                + ", operUrl=" + operUrl + ", operIp=" + operIp + ", operLocation=" + operLocation + ", operParam="
                + operParam + ", status=" + status + ", errorMsg=" + errorMsg + ", operTime=" + operTime + "]";
    }

}
