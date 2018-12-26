package com.atgugui.consumer.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.curator.shaded.com.google.common.base.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atgugui.cache.BaseCacheService;
import com.atgugui.common.constant.JedisConstants;
import com.atgugui.common.utils.AppUtil;
import com.atgugui.common.utils.EmojiUtil;
import com.atgugui.common.utils.ListUtil;
import com.atgugui.config.RedisUtil;
import com.atgugui.consumer.BlogService;
import com.atgugui.enums.exceptionals.StateEnum;
import com.atgugui.exceptions.BaseException;
import com.atgugui.facade.BlogFacade;
import com.atgugui.model.blog.BlogListBox;
import com.atgugui.result.RestResponse;

@Service
public class BlogServiceImpl implements BlogService {
	
	@Reference
	private BlogFacade blogFacade;
	
	 @Autowired
	 private RedisUtil redisUtil; //注入redis
	 
	 @Autowired
	 private BaseCacheService baseCacheService;

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
		//我应该去操作一下缓存的 , 新增的时候删除缓存
		baseCacheService.insertBlogAndCache(blogListBox , info , JedisConstants.GET_BLOG_LIST_CACHE);
		return new RestResponse<StateEnum>(StateEnum.SUCCESS_OPTION);
	}

	/* 通用接口具有查询功能
	 * 
	 */
	@Override
	public RestResponse<List<BlogListBox>> getBlogList(BlogListBox blogListBox , Integer page , Integer pageSize) {
		//获取所有的博客的信息 , 从缓存当中获取
		List<BlogListBox> list = baseCacheService.getBlogList(JedisConstants.GET_BLOG_LIST_CACHE);
		if (AppUtil.isNotNull(blogListBox) && AppUtil.isNotNull(blogListBox.getBlogType())) 
		{
			list = list.stream().filter(t -> 
			//过滤条件 , 使用了jdk1.8 流的写法
			Objects.equal(t.getBlogType() , blogListBox.getBlogType()) 
			).collect(Collectors.toList());
		}
		if (AppUtil.isNotNull(blogListBox) && AppUtil.isNotNull(blogListBox.getBlogName())) 
		{
			list = list.stream().filter(t -> 
			//通过indexof模糊查询
			blogListBox.getBlogName().indexOf(t.getBlogName()) >= 0
					).collect(Collectors.toList());
		}
		//分页
		List<BlogListBox> pageList = ListUtil.getPageList(list, page, pageSize);
		return new RestResponse<List<BlogListBox>>(pageList);
	}
	
}
