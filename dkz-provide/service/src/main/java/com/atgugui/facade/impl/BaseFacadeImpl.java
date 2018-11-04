package com.atgugui.facade.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.atgugui.common.constant.MybatisPlusConstants;
import com.atgugui.facade.BaseFacade;
import com.atgugui.jdbc.user.SysDictDataMapper;
import com.atgugui.model.sys.SysDictData;


@Component
@Service
public class BaseFacadeImpl implements BaseFacade{
	
	@Autowired
	private SysDictDataMapper sysDictDataMapper; 
	
	@Override
	public List<SysDictData> getdictData(String blogTitleList) {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put(MybatisPlusConstants.SYS_DICT_DATA_DICT_TYPE, blogTitleList);
		List<SysDictData> selectByMap = sysDictDataMapper.selectByMap(columnMap);
		return selectByMap;
	}
	
}
