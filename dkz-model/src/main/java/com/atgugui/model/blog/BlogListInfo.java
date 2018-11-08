package com.atgugui.model.blog;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;



/**
 * <p>
 * 
 * </p>
 *
 * @author conlon123
 * @since 2018-11-08
 */
@TableName("blog_list_info")
public class BlogListInfo extends Model<BlogListInfo> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6321517456490211164L;
	private Long id;
    /**
     * 博客列表id
     */
    @TableField("blog_list_id")
    private Long blogListId;
    /**
     * 博客内容
     */
    @TableField("blog_info")
    private String blogInfo;
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


	public Long getBlogListId() {
		return blogListId;
	}


	public void setBlogListId(Long blogListId) {
		this.blogListId = blogListId;
	}


	public String getBlogInfo() {
		return blogInfo;
	}


	public void setBlogInfo(String blogInfo) {
		this.blogInfo = blogInfo;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

    
    
    
}
