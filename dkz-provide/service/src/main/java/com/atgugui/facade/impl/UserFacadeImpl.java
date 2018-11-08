package com.atgugui.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.atgugui.enums.exceptionals.StateEnum;
import com.atgugui.exceptions.BaseException;
import com.atgugui.facade.UserFacade;
import com.atgugui.jdbc.user.BizUserMapper;
import com.atgugui.model.user.BizUser;

@Component
@Service
public class UserFacadeImpl implements UserFacade {

	@Autowired
	private BizUserMapper bizUserMapper;
	
	@Override
	public BizUser getUserByUser(BizUser bizUser) {
		//查询用户信息
		BizUser selectOneBizUser = bizUserMapper.selectOne(bizUser);
		return selectOneBizUser;
	}

	@Override
	@Transactional
	public int updateBizUser(BizUser bizUser) {
		Integer updateById = bizUserMapper.updateById(bizUser);
		if (updateById != 1) {
			throw new BaseException(StateEnum.ERROR_SYSTEM);
		}
		return updateById;
	}

	@Override
	@Transactional
	public int insertBizUser(BizUser bizUser) {
		Integer insert = bizUserMapper.insert(bizUser);
		if (insert != 1) {
			throw new BaseException(StateEnum.ERROR_SYSTEM);
		}
		return insert;
	}

	@Override
	public BizUser getBaseUser(Integer userId) {
		return bizUserMapper.getBaseUser(userId);
	}

}
