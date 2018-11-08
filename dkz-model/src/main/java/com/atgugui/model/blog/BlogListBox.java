package com.atgugui.model.blog;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;



/**
 * <p>
 * 博客列表
 * </p>
 *
 * @author conlon123
 * @since 2018-11-08
 */
@TableName("blog_list_box")
public class BlogListBox extends Model<BlogListBox> {


    /**
	 * 
	 */
	private static final long serialVersionUID = 3205570599717481655L;
	
	private Long id;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 文章名称
     */
    @TableField("blog_name")
    private String blogName;
    /**
     * 博客摘要(截取文章一小段)
     */
    @TableField("blog_digest")
    private String blogDigest;
    
    /**
     * 博客类型
     */
    @TableField("blog_type")
    private String blogType;
    
    /**
     * ip地址
     */
    @TableField("create_ip")
    private String createIp;
    /**
     * 阅读量
     */
    @TableField("read_number")
    private Integer readNumber;
    /**
     * 评论数量
     */
    @TableField("comment_number")
    private Integer commentNumber;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;


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


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getBlogName() {
		return blogName;
	}


	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}


	public String getBlogDigest() {
		return blogDigest;
	}


	public void setBlogDigest(String blogDigest) {
		this.blogDigest = blogDigest;
	}


	public Integer getReadNumber() {
		return readNumber;
	}


	public void setReadNumber(Integer readNumber) {
		this.readNumber = readNumber;
	}


	public Integer getCommentNumber() {
		return commentNumber;
	}


	public void setCommentNumber(Integer commentNumber) {
		this.commentNumber = commentNumber;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public String getCreateIp() {
		return createIp;
	}


	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}


	@Override
	public String toString() {
		return "BlogListBox [id=" + id + ", userId=" + userId + ", blogName=" + blogName + ", blogDigest=" + blogDigest
				+ ", blogType=" + blogType + ", createIp=" + createIp + ", readNumber=" + readNumber
				+ ", commentNumber=" + commentNumber + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}

    
    
    
    
}
