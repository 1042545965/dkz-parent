package com.atgugui.cache;

import java.io.Serializable;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atgugui.facade.UserFacade;
import com.atgugui.model.user.BizUser;

/**
 * @author dkzadmin
 * 所有更新bizuser对象的方法都要放到这个里面来
 */
@Service
@CacheConfig(cacheNames  = "bserCacheService")
public class UserCacheService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8472248233865383161L;

	
	@Reference
    private UserFacade userFacade;

	@Cacheable(key="#userId")
	public BizUser getBaseUser(Integer userId) {
		return userFacade.getBaseUser(userId);
	}
	


}
