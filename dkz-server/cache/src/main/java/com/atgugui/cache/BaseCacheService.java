package com.atgugui.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atgugui.common.constant.HomeContants;
import com.atgugui.common.utils.StringUtils;
import com.atgugui.enums.mybatisplus.MybatisPlusEnum;
import com.atgugui.facade.BaseFacade;
import com.atgugui.facade.BlogFacade;
import com.atgugui.model.blog.BlogListBox;
import com.atgugui.model.sys.SysDictData;
import com.atgugui.vo.TitleVO;

@Service
@CacheConfig(cacheNames  = "baseCacheService")
public class BaseCacheService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8472248233865383161L;

	
	@Reference
    private BaseFacade baseFacade;
	
	@Reference
	private BlogFacade blogFacade;
	
//	@CacheEvict(key="#homePagetaTitle")
	@Cacheable(key="#homePagetaTitle")
	public List<TitleVO> getHomePagetaTitle(String homePagetaTitle) {
		List<SysDictData> sysDictDataList = baseFacade.getdictData(HomeContants.BLOG_TITLE_LIST);
		List<TitleVO> list = new ArrayList<>();
		for (SysDictData sysDictData : sysDictDataList) {
			TitleVO titleVO = StringUtils.strToObj(sysDictData.getDictValue(), TitleVO.class);
			list.add(titleVO);
		}
		return list;
	}
	
	/** 存储后删除缓存
	 * @param blogListBox
	 * @param info
	 * @param getBlogListCache
	 * @return
	 */
	@CacheEvict(key="#getBlogListCache")
	public BlogListBox insertBlogAndCache(BlogListBox blogListBox, String info, String getBlogListCache) {
		BlogListBox insertBlog = blogFacade.insertBlog(blogListBox , info);
		return insertBlog;
	}

	@Cacheable(key="#getBlogListCache")
	public List<BlogListBox> getBlogList(String getBlogListCache) {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put(MybatisPlusEnum.BLOG_LIST_BOX_DELETE_STATUS.getCode(), Integer.parseInt(MybatisPlusEnum.BLOG_LIST_BOX_DELETE_STATUS.getInfo()));
		//查出所有博客信息
		List<BlogListBox> list = blogFacade.getBlogList(columnMap);
		return list;
	}

}
