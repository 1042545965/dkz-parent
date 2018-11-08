package com.atgugui.consumer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atgugui.common.constant.JedisConstants;
import com.atgugui.common.utils.AppUtil;
import com.atgugui.common.utils.EmojiUtil;
import com.atgugui.config.RedisUtil;
import com.atgugui.consumer.BlogService;
import com.atgugui.enums.exceptionals.StateEnum;
import com.atgugui.enums.exceptionals.user.UserExceptionEnum;
import com.atgugui.exceptions.BaseException;
import com.atgugui.exceptions.user.UserException;
import com.atgugui.facade.BlogFacade;
import com.atgugui.model.blog.BlogListBox;
import com.atgugui.result.RestResponse;

@Service
public class BlogServiceImpl implements BlogService {
	
	@Reference
	private BlogFacade blogFacade;
	
	 @Autowired
	    private RedisUtil redisUtil; //注入redis

	@Override
	public RestResponse<StateEnum> insertBlog(BlogListBox blogListBox, String token) {
		Object hget = redisUtil.hget(JedisConstants.SYSTEM_TOKEN_TO_USER, token);
		if (AppUtil.isNull(hget)) 
		{//未登录
			 throw new BaseException(StateEnum.ERROR_USER_NOT_LOGIN);
		}
		Long userId = (Long) hget;
		if (AppUtil.isNull(blogListBox) || AppUtil.isNull(blogListBox.getBlogName()) || AppUtil.isNull(blogListBox.getBlogDigest())) 
		{
			throw new BaseException(StateEnum.ERROR_PARAMETE);
		}
		String info  = blogListBox.getBlogDigest();
		//截取摘要
		String digest = info.substring(0, info.length()/3);
		blogListBox.setBlogDigest(digest);
		blogListBox.setUserId(userId);
		//处理emoji表情 , 奖期转编码
		info = EmojiUtil.emojiToStr(info);
		blogFacade.insertBlog(blogListBox , info);
		return null;
	}
	
}
