package com.atgugui.async.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.atgugui.asyn.jdbc.NotifyMapper;
import com.atgugui.asyn.jdbc.SysOperLogMapper;
import com.atgugui.facade.AsyncFacade;
import com.atgugui.model.Notify;
import com.atgugui.model.operlog.SysOperLog;
@Component
@Service
public class AsyncFacadeImpl implements AsyncFacade {
	
	@Autowired
	private NotifyMapper notifyMapper;
	
	@Autowired
	private SysOperLogMapper sysOperLogMapper;
	
	/* 异步操作 , 指定五秒后开始执行
	 * 记录用户发送的验证码
	 */
	@Override
	@Async
	public Integer insertNotify(Notify notify2) {
		 Integer insert = notifyMapper.insert(notify2);
		return insert;
	}

	/* 记录用户的操作日志
	 * 记录日志不需要使用事物 , 没记录上去也不要回滚
	 */
	@Override
	@Async
	public Integer insertSysOperLog(SysOperLog operLog) {
			Integer insert = sysOperLogMapper.insert(operLog);
			return insert;
	}
	
}
