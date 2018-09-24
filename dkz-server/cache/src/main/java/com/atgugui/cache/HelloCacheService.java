package com.atgugui.cache;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atgugui.config.RedisUtil;
import com.atgugui.facade.HelloFacade;
import com.atgugui.model.Employee;

/**
 * @program: spring-boot
 * @description: 所有走缓存的基类查询全部都要放置在这里面 , 分模块创建缓存
 * @author: conlon
 * @create:  2018-08-20 10:42  
 **/
@Service
@CacheConfig(cacheNames  = "helloCacheService")
public class HelloCacheService implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5460022300369443297L;
	@Autowired
    private RedisUtil redisUtil;
	@Reference
    private HelloFacade helloFacade;
    
    @Cacheable(key = "#key + #id")
    public Employee getEmployee(String key , Integer id) {
        return helloFacade.selectCacheById(id);
    }

    public Employee getRedisEmployee(Integer id) {
        Object obj =  redisUtil.get("dkzredis");
        if(StringUtils.isEmpty(obj)){
            Employee employee = helloFacade.selectCacheById(id);
            redisUtil.set("dkzredis",employee);
            return employee;
        }else {
            return (Employee)obj;
        }
    }

}
