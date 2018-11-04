package com.atgugui.consumer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atgugui.cache.BaseCacheService;
import com.atgugui.common.constant.JedisConstants;
import com.atgugui.consumer.HomePagetaTitleService;
import com.atgugui.facade.BaseFacade;
import com.atgugui.result.RestResponse;
import com.atgugui.vo.TitleVO;

@Service
public class HomePagetaTitleServiceImpl implements HomePagetaTitleService {
	@Reference
    private BaseFacade baseFacade; //注入消费者
	
	
	/**使用基础缓存
	 * 
	 */
	@Autowired
	private BaseCacheService baseCacheService;


	@Override
	public RestResponse<List<TitleVO>> getHomePagetaTitle() {
		List<TitleVO> titleVOList = baseCacheService.getHomePagetaTitle(JedisConstants.HOME_PAGETA_TITLE);
		return new RestResponse<List<TitleVO>>(titleVOList);
	}
	
}
