package com.atgugui.facade.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.atgugui.common.utils.LogUtils;
import com.atgugui.enums.exceptionals.StateEnum;
import com.atgugui.exceptions.BaseException;
import com.atgugui.facade.BlogFacade;
import com.atgugui.jdbc.blog.BlogListBoxMapper;
import com.atgugui.jdbc.blog.BlogListInfoMapper;
import com.atgugui.model.blog.BlogListBox;
import com.atgugui.model.blog.BlogListInfo;

@Component
@Service
public class BlogFacadeImpl implements BlogFacade{
	@Autowired
	private BlogListBoxMapper blogListBoxMapper;
	
	@Autowired
	private BlogListInfoMapper blogListInfoMapper;

	@Override
	@Transactional
	public BlogListBox insertBlog(BlogListBox blogListBox, String info) {
		blogListBoxMapper.insert(blogListBox);
		if (blogListBoxMapper.insert(blogListBox) !=1) 
		{
			LogUtils.logError("blogListBox 插入数据为{}"+blogListBox.toString(), null);
			throw new BaseException(StateEnum.ERROR_SYSTEM);
		}
		BlogListInfo blogListInfo = new BlogListInfo();
		blogListInfo.setBlogInfo(info);
		blogListInfo.setBlogListId(blogListBox.getId());
		blogListInfo.setCreateTime(new Date());
		if (blogListInfoMapper.insert(blogListInfo) != 1)
		{
			LogUtils.logError("blogListInfo 插入数据为{}"+blogListBox.toString(), null);
			throw new BaseException(StateEnum.ERROR_SYSTEM);
		}
		return blogListBox;
	}
	
}
