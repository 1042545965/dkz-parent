package com.atgugui.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atgugui.common.constant.HomeContants;
import com.atgugui.common.utils.StringUtils;
import com.atgugui.facade.BaseFacade;
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

}
