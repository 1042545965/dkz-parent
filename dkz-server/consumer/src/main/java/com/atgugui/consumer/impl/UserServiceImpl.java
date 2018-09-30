package com.atgugui.consumer.impl;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atgugui.common.constant.Constants;
import com.atgugui.common.constant.UserConstants;
import com.atgugui.common.utils.AppUtil;
import com.atgugui.common.utils.CheckUtil;
import com.atgugui.common.utils.DateUtils;
import com.atgugui.common.utils.MessageUtils;
import com.atgugui.common.utils.ServletUtils;
import com.atgugui.config.RedisUtil;
import com.atgugui.consumer.UserService;
import com.atgugui.enums.exceptionals.user.UserExceptionEnum;
import com.atgugui.enums.user.UserStatus;
import com.atgugui.exceptions.user.UserException;
import com.atgugui.facade.UserFacade;
import com.atgugui.manager.AsyncManager;
import com.atgugui.manager.factory.AsyncFactory;
import com.atgugui.model.user.BizUser;
import com.atgugui.result.BaseResult;

public class UserServiceImpl implements UserService {
    @Reference
    private UserFacade userFacade; //注入消费者
	
    @Autowired
    private RedisUtil redisUtil; //注入redis
    
    private Map<String, AtomicInteger> map ; // 记录用户登录密码输入失败次数
    
	@Override
	public BaseResult userLogin(String userName, String password) {
		//用户名密码是否为空
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) 
		{
			 AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGIN_FAIL, MessageUtils.message("not.null")));
			 throw new UserException(UserExceptionEnum.ERROR_USER_LOGIN_USERNAME_AND_PASSWORD_NONE);
		}
		//验证密码格式是否正确
		if (!CheckUtil.checkPassword(password))
        {
			//异步记录用户登录失败的原因
			AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
			throw new UserException(UserExceptionEnum.ERROR_USER_LOGIN_PASSWORD_LENGTH);
        }
		
		//查询用户的信息
		BizUser bizUser = new BizUser();
		bizUser.setUserName(userName);
		BizUser user = userFacade.getUserByUser(bizUser);
		//手机号码登录
		if (AppUtil.isNull(user) && CheckUtil.maybeMobilePhoneNumber(userName))
		{
			bizUser.setPhonenumber(userName);
			user = userFacade.getUserByUser(bizUser);
		}
		//邮箱登录
		if (AppUtil.isNull(user) && CheckUtil.checkEmail(userName))
		{
			bizUser.setEmail(userName);
			user = userFacade.getUserByUser(bizUser);
		}
		//用户不存在
		if (user == null)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGIN_FAIL, MessageUtils.message("user.not.exists")));
            throw new UserException(UserExceptionEnum.ERROR_USER_LOGIN_USERNAME_NONE);
        }
		//用户已被删除
        if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGIN_FAIL, MessageUtils.message("user.password.delete")));
            throw new UserException(UserExceptionEnum.ERROR_USER_LOGIN_USERNAME_NONE);
        }
        //用户被停用
        if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGIN_FAIL, MessageUtils.message("user.blocked", user.getRemark())));
            throw new UserException(UserExceptionEnum.ERROR_USER_LOGIN_USERNAME_NONE);
        }
        //校验密码
        validate(bizUser, password);
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        bizUser.setLoginIp(ServletUtils.getIP());
        bizUser.setLoginDate(DateUtils.getNowDate());
        int i = userFacade.updateBizUser(bizUser);
		return null;
	}
	
	
	private void validate(BizUser user , String password) {
		//获取用户登录的次数
		AtomicInteger atomicInteger = (AtomicInteger)redisUtil.get(UserConstants.USER_PASSWORD_COUNT_ERROR+user.getUserId());
		atomicInteger = AppUtil.isNull(atomicInteger)?new AtomicInteger(0):atomicInteger;
		//超过限制登录次数
		if (atomicInteger.incrementAndGet() > UserConstants.USER_PASSWORD_COUNT_SUM) {
			AsyncManager.me().execute(AsyncFactory.recordLogininfor(user.getUserName(), Constants.LOGIN_FAIL, MessageUtils.message("user.password.retry.limit.exceed", atomicInteger)));
			throw new UserException(UserExceptionEnum.ERROR_USER_LOGIN_USERNAME_PASS_NUMBER);
		}
		//密码校验失败
		if (!matches(user, password)) 
		{
			AsyncManager.me().execute(AsyncFactory.recordLogininfor(user.getUserName(), Constants.LOGIN_FAIL, MessageUtils.message("user.password.retry.limit.count", atomicInteger)));
			//放入缓存并且定时
			redisUtil.lSet(UserConstants.USER_PASSWORD_COUNT_ERROR+user.getUserId(), atomicInteger, UserConstants.USER_ACCOUNT_FREEZE_TIME*60);
			throw new UserException(UserExceptionEnum.ERROR_USER_LOGIN_USERNAME_PASS_NUMBER);
		}
	};
	
	/** md5 盐值加密校验
	 * @param user
	 * @param newPassword
	 * @return
	 */
	private boolean matches(BizUser user, String newPassword)
    {
        return user.getPassword().equals(encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
    }


	private String encryptPassword(String username, String password, String salt)
    {
        return new Md5Hash(username + password + salt).toHex().toString();
    }
}
