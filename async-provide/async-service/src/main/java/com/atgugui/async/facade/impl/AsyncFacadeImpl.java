package com.atgugui.async.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.atgugui.asyn.jdbc.NotifyMapper;
import com.atgugui.facade.AsyncFacade;
import com.atgugui.model.Notify;
@Component
@Service
public class AsyncFacadeImpl implements AsyncFacade {
	
	@Autowired
	private NotifyMapper notifyMapper;
	
	/* 异步操作 , 指定五秒后开始执行
	 * 
	 */
	@Override
	@Async(value="5")
	public Integer insertNotify(Notify notify2) {
		 Integer insert = notifyMapper.insert(notify2);
		return insert;
	}
	
}
