package com.atgugui.model.operlog;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 操作日志记录
 * </p>
 *
 * @author wangqichang123
 * @since 2018-10-09
 */
@TableName("sys_oper_log")
public class SysOperLog extends Model<SysOperLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 日志主键
     */
    @TableId(value = "oper_id", type = IdType.AUTO)
    private Integer operId;
    /**
     * 模块标题
     */
    private String title;
    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @TableField("business_type")
    private Integer businessType;
    /**
     * 方法名称
     */
    private String method;
    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     */
    @TableField("operator_type")
    private Integer operatorType;
    /**
     * 操作人员
     */
    @TableField("oper_name")
    private String operName;
    /**
     * 部门名称
     */
    @TableField("dept_name")
    private String deptName;
    /**
     * 请求URL
     */
    @TableField("oper_url")
    private String operUrl;
    /**
     * 主机地址
     */
    @TableField("oper_ip")
    private String operIp;
    /**
     * 操作地点
     */
    @TableField("oper_location")
    private String operLocation;
    /**
     * 请求参数
     */
    @TableField("oper_param")
    private String operParam;
    /**
     * 操作状态（0正常 1异常）
     */
    private Integer status;
    /**
     * 错误消息
     */
    @TableField("error_msg")
    private String errorMsg;
    /**
     * 操作时间
     */
    @TableField("oper_time")
    private Date operTime;


    @Override
    protected Serializable pkVal() {
        return this.operId;
    }


	public Integer getOperId() {
		return operId;
	}


	public void setOperId(Integer operId) {
		this.operId = operId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Integer getBusinessType() {
		return businessType;
	}


	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}


	public String getMethod() {
		return method;
	}


	public void setMethod(String method) {
		this.method = method;
	}


	public Integer getOperatorType() {
		return operatorType;
	}


	public void setOperatorType(Integer operatorType) {
		this.operatorType = operatorType;
	}


	public String getOperName() {
		return operName;
	}


	public void setOperName(String operName) {
		this.operName = operName;
	}


	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public String getOperUrl() {
		return operUrl;
	}


	public void setOperUrl(String operUrl) {
		this.operUrl = operUrl;
	}


	public String getOperIp() {
		return operIp;
	}


	public void setOperIp(String operIp) {
		this.operIp = operIp;
	}


	public String getOperLocation() {
		return operLocation;
	}


	public void setOperLocation(String operLocation) {
		this.operLocation = operLocation;
	}


	public String getOperParam() {
		return operParam;
	}


	public void setOperParam(String operParam) {
		this.operParam = operParam;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getErrorMsg() {
		return errorMsg;
	}


	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}


	public Date getOperTime() {
		return operTime;
	}


	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

}
