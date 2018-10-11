package com.atgugui.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;


/**
 * <p>
 * 
 * </p>
 *
 * @author wangqichang123
 * @since 2018-10-11
 */
@TableName("notify")
public class Notify extends Model<Notify> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 10 注册
     */
    private Integer type;
    /**
     * 验证码详情
     */
    private String content;
    /**
     * 1:发送成功 ,2 失败
     */
    @TableField("notify_status")
    private Integer notifyStatus;
    @TableField("create_time")
    private Date createTime;

    
    
    

    @Override
    protected Serializable pkVal() {
        return this.id;
    }





	public Long getId() {
		return id;
	}





	public void setId(Long id) {
		this.id = id;
	}





	public Integer getType() {
		return type;
	}





	public void setType(Integer type) {
		this.type = type;
	}





	public String getContent() {
		return content;
	}





	public void setContent(String content) {
		this.content = content;
	}





	public Integer getNotifyStatus() {
		return notifyStatus;
	}





	public void setNotifyStatus(Integer notifyStatus) {
		this.notifyStatus = notifyStatus;
	}





	public Date getCreateTime() {
		return createTime;
	}





	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
