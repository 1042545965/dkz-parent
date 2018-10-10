package com.atgugui.async.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.atgugui.asyn.jdbc.SysOperLogMapper;
import com.atgugui.facade.AsyncFacade;
@Component
@Service
public class AsyncFacadeImpl implements AsyncFacade {
	
	@Autowired
	private SysOperLogMapper sysOperLogMapper;
	
}
