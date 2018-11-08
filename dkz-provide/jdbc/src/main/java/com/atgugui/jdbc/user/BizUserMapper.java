package com.atgugui.jdbc.user;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.atgugui.model.user.BizUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * @author dkzadmin
 * 
 */
public interface BizUserMapper  extends BaseMapper<BizUser> {

	@Select("SELECT a.user_id , a.nick_name , a.user_name , a.remark FROM `blog_biz_user` a WHERE a.user_id = #{userId}")
	BizUser getBaseUser(@Param("userId")Integer userId);
	
}