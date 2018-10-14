package com.atgugui.consumer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atgugui.cache.HelloCacheService;
import com.atgugui.common.constant.JedisConstants;
import com.atgugui.exceptions.BaseException;
import com.atgugui.facade.HelloFacade;
import com.atgugui.model.Employee;

@Service
public class HelloService{

//	@Reference(mock="DUBBO.ERROR.HELLO")
	@Reference
    private HelloFacade helloFacade; //注入消费者
    
    @Autowired
    private HelloCacheService helloCacheService; //使用spring的注解缓存

    public String hello(){
    	String str = helloFacade.getStr();
		return str;
    }

	public Employee insert() throws BaseException{
		Employee employeein = new Employee();
		employeein.setEmail("email");
		employeein.setGender(1);
		employeein.setLastName("lastName");
		employeein.setdId(1);
		Employee employee = helloFacade.insert(employeein);
		return employee;
	}

    public Employee getByCache(Integer id){
    	String key = JedisConstants.STOCK_PLEDGE_SYSTEM_PARAMS;
		Employee employee = helloCacheService.getEmployee(key, id);
		return employee;
    }
}
