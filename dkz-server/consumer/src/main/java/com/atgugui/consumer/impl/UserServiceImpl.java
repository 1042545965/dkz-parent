package com.atgugui.consumer.impl;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atgugui.cache.UserCacheService;
import com.atgugui.common.constant.Constants;
import com.atgugui.common.constant.JedisConstants;
import com.atgugui.common.constant.UserConstants;
import com.atgugui.common.utils.AppUtil;
import com.atgugui.common.utils.CheckUtil;
import com.atgugui.common.utils.DateUtils;
import com.atgugui.common.utils.LogUtils;
import com.atgugui.common.utils.Md5Util;
import com.atgugui.common.utils.NumberUtil;
import com.atgugui.common.utils.SaltUtil;
import com.atgugui.common.utils.ServletUtils;
import com.atgugui.config.RedisUtil;
import com.atgugui.consumer.UserService;
import com.atgugui.enums.exceptionals.StateEnum;
import com.atgugui.enums.exceptionals.user.UserExceptionEnum;
import com.atgugui.enums.user.UserStatus;
import com.atgugui.exceptions.BaseException;
import com.atgugui.exceptions.user.UserException;
import com.atgugui.facade.AsyncFacade;
import com.atgugui.facade.UserFacade;
import com.atgugui.manager.AsyncManager;
import com.atgugui.manager.factory.AsyncFactory;
import com.atgugui.model.Notify;
import com.atgugui.model.user.BizUser;
import com.atgugui.result.RestResponse;
import com.atgugui.vo.BizUserVo;

@Service
public class UserServiceImpl implements UserService {
//    @Reference(mock="DUBBO.ERROR.USER")
	@Reference
    private UserFacade userFacade; //注入消费者
	
    @Autowired
    private RedisUtil redisUtil; //注入redis
    
//    @Reference(mock="DUBBO.ERROR.ASYNC")
    @Reference
    private AsyncFacade asyncFacade; //注入异步provide,记录日志
    
    @Autowired
    private UserCacheService userCacheService;
    
	/* 用户注册
	 * 
	 */
	@Override
	public RestResponse<BizUser> userRegister(BizUser bizUser) {
		if (AppUtil.isNull(bizUser) || AppUtil.isNull(bizUser.getEmail()) || AppUtil.isNull(bizUser.getPhonenumber()) ||
				AppUtil.isNull(bizUser.getUserName())  || AppUtil.isNull(bizUser.getPassword())) 
		{
			//这里我自己的设想是使用spring的异步操作, 新建一个异步的provide . 这个无所谓不用与业务耦合 ,
			 throw new BaseException(StateEnum.ERROR_PARAMETE);
		}
		//校验手机号码
		if (!CheckUtil.maybeMobilePhoneNumber(bizUser.getPhonenumber())) 
		{
			 throw new UserException(UserExceptionEnum.ERROR_USER_REGISTER_PHONE_NUMBER_ERROR);
		}
		//校验密码格式是否正确
		if (!CheckUtil.checkPassword(bizUser.getPassword())) 
		{
			throw new UserException(UserExceptionEnum.ERROR_USER_REGISTER_PASSWORD_NOT_MATCH_ERROR);
		}
		
		//校验密码格式是否正确
		if (!CheckUtil.checkEmail(bizUser.getEmail())) 
		{
			throw new UserException(UserExceptionEnum.ERROR_USER_REGISTER_EMAIL_ERROR);
		}
		//校验验证码
		/*Notify notify = (Notify)redisUtil.get(bizUser.getPhonenumber());
		if (AppUtil.isNull(notify)) 
		{//验证码已失效
			throw new UserException(UserExceptionEnum.ERROR_USER_REGISTER_PASSWORD_NOT_MATCH_ERROR);
		}*/
		//该手机号码是否被注册过
		BizUser bizUser2 = new BizUser();
		bizUser2.setPhonenumber(bizUser.getPhonenumber());
		BizUser userByUser = userFacade.getUserByUser(bizUser2);
		if (AppUtil.isNotNull(userByUser)) 
		{//该号码已被注册
			throw new UserException(UserExceptionEnum.ERROR_USER_REGISTER_PHONE_REPEAT_ERROR);
		}
		if (AppUtil.isNotNull(userByUser) && Objects.equals(userByUser.getEmail(), bizUser.getEmail())) 
		{//邮箱被注册
			throw new UserException(UserExceptionEnum.ERROR_USER_REGISTER_PHONE_REPEAT_ERROR);
		}
		//生成用户加盐码
		String salt = SaltUtil.getSalt();
		bizUser.setSalt(salt);
		String md5password = Md5Util.md5(bizUser.getPassword());
		//加盐密码
		String password = Md5Util.encryptPassword(md5password, salt);
		bizUser.setPassword(password);
		//注册用户
		userFacade.insertBizUser(bizUser);
		return new RestResponse<BizUser>(bizUser);
	}
	
	
	
	//用户登录
	@Override
	public RestResponse<BizUserVo> userLogin(String userName, String password) {
		//用户名密码是否为空
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) 
		{
			 AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGIN_FAIL, UserExceptionEnum.ERROR_USER_LOGIN_USERNAME_AND_PASSWORD_NONE.getMessage()));
			 throw new UserException(UserExceptionEnum.ERROR_USER_LOGIN_USERNAME_AND_PASSWORD_NONE);
		}
		//验证密码格式是否正确
		if (!CheckUtil.checkPassword(password))
        {
			//异步记录用户登录失败的原因
			AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGIN_FAIL, UserExceptionEnum.ERROR_USER_LOGIN_PASSWORD_LENGTH.getMessage()));
			throw new UserException(UserExceptionEnum.ERROR_USER_LOGIN_PASSWORD_LENGTH);
        }
		
		//查询用户的信息 , 感觉这里的查询会有点问题  , 暂时先记录一下
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
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGIN_FAIL, UserExceptionEnum.ERROR_USER_LOGIN_USERNAME_NONE.getMessage()));
            throw new UserException(UserExceptionEnum.ERROR_USER_LOGIN_USERNAME_NONE);
        }
		//用户已被删除
        if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGIN_FAIL, UserExceptionEnum.ERROR_USER_LOGIN_USERNAME_IS_DELETE.getMessage()));
            throw new UserException(UserExceptionEnum.ERROR_USER_LOGIN_USERNAME_IS_DELETE);
        }
        //用户被停用
        if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGIN_FAIL, UserExceptionEnum.ERROR_USER_LOGIN_USERNAME_STOP_USE.getMessage()));
            throw new UserException(UserExceptionEnum.ERROR_USER_LOGIN_USERNAME_STOP_USE);
        }
        //校验密码
        validate(user, password);
//        AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        user.setLoginIp(ServletUtils.getIP());
        user.setLoginDate(DateUtils.getNowDate());
        //修改最后登录时间和ip
        userFacade.updateBizUser(user);
        //生成token
        String token = Md5Util.md5(""+user.getUserId()+System.currentTimeMillis() / 1000);
        //将userid与token绑定
        redisUtil.hset(JedisConstants.SYSTEM_USER_TO_TOKEN, user.getUserId().toString() , token);
        //将token与userid绑定
        redisUtil.hset(JedisConstants.SYSTEM_TOKEN_TO_USER, token, user.getUserId());
        //查询用户的基础的一些信息返回  , 放入缓存
        BizUser baseUser = userCacheService.getBaseUser(user.getUserId());
        BizUserVo bizUserVo = new BizUserVo();
        bizUserVo.setToken(token);
        bizUserVo.setBizUser(baseUser);
		return new RestResponse<BizUserVo>(bizUserVo);
	}
	
	
	private void validate(BizUser user , String password) {
		//获取用户登录的次数
		Object object = redisUtil.get(UserConstants.USER_PASSWORD_COUNT_ERROR+user.getUserId());
		AtomicInteger atomicInteger;
		atomicInteger = AppUtil.isNull(object)?new AtomicInteger(0):(AtomicInteger)object;
		//超过限制登录次数
		if (atomicInteger.incrementAndGet() > UserConstants.USER_PASSWORD_COUNT_SUM) {
			AsyncManager.me().execute(AsyncFactory.recordLogininfor(user.getUserName(), Constants.LOGIN_FAIL, UserExceptionEnum.ERROR_USER_LOGIN_USERNAME_PASS_NUMBER.getMessage()+atomicInteger));
			throw new UserException(UserExceptionEnum.ERROR_USER_LOGIN_USERNAME_PASS_NUMBER);
		}
		//密码校验失败
		if (!matches(user, password)) 
		{
			AsyncManager.me().execute(AsyncFactory.recordLogininfor(user.getUserName(), Constants.LOGIN_FAIL, UserExceptionEnum.ERROR_USER_LOGIN_PASSWORD_ERROR.getMessage()+atomicInteger));
			//放入缓存并且定时
			redisUtil.set(UserConstants.USER_PASSWORD_COUNT_ERROR+user.getUserId(), atomicInteger, UserConstants.USER_ACCOUNT_FREEZE_TIME*60);
			throw new UserException(UserExceptionEnum.ERROR_USER_LOGIN_PASSWORD_ERROR);
		}
	};
	
	/** md5 盐值加密校验 , 这里用的是shiro提供的md5 盐值加密方式
	 * @param user
	 * @param newPassword
	 * @return
	 */
	private boolean matches(BizUser user, String newPassword)
    {
		return Objects.equals(user.getPassword(), Md5Util.encryptPassword(Md5Util.md5(newPassword), user.getSalt()));
    }

	/* 
	 * 发送验证码  , 并保存到数据库
	 */
	@Override
	public RestResponse<StateEnum> getValidateCode(String userPhone) {
		//校验手机号码
		if (!CheckUtil.maybeMobilePhoneNumber(userPhone)) 
		{
			throw new BaseException(StateEnum.ERROR_PHONE);
		}
		//查看手机号是否被注册过
		BizUser bizUser = new BizUser();
		bizUser.setPhonenumber(userPhone);
		BizUser user = userFacade.getUserByUser(bizUser);
		if (AppUtil.isNotNull(user)) 
		{//该号码已被注册
			throw new UserException(UserExceptionEnum.ERROR_USER_REGISTER_PHONE_REPEAT_ERROR);
		}
		//查看该手机号码是否在规定的时间内发送过验证码 60s
		Notify notify = (Notify)redisUtil.get(userPhone);
		if (AppUtil.isNotNull(notify)) 
		{
			Date createTime = notify.getCreateTime();
			Long time = createTime.getTime();
			Long currentTimeMillis = System.currentTimeMillis()/1000;
			if (currentTimeMillis - time <= Constants.VALIDETE_INTERVAL_TIME*60) {
				throw new UserException(UserExceptionEnum.ERROR_USER_REGISTER_VALIDATE_INTERVAL_TIME_ERROR);
			}
		}
		else
		{//验证码已失效
			throw new UserException(UserExceptionEnum.ERROR_USER_REGISTER_VALIDATE_CODE_LOSS_ERROR);
		}
		//随机6位数验证码
		Long validateCode = NumberUtil.generateRandomNumber(6);
		Notify notify2 = new Notify();
		notify2.setPhoneNumber(userPhone);
		notify2.setContent(UserConstants.USER_REGISTER_MARKED_WORD+validateCode);
		notify2.setCreateTime(new Date());
		notify2.setType(10);//10 代表注册
		//发送成功或者失败我是需要记录一下,这里直接捕捉发送方法的异常来判断  . 目前自己的打算发邮件的方式 !!
		notify2.setNotifyStatus(1);
		//将验证码放到缓存当中,并且设置过期时间 30min
		redisUtil.set(userPhone , notify2 , Constants.VALIDETE_TIME_OUT*60);
		//调用异步方法保存验证码 , 不用回滚,插入失败也没有关系
		try 
		{
			asyncFacade.insertNotify(notify2);
		} catch (Exception e) 
		{
			LogUtils.logError(e.getMessage(), e);
		}
		return new RestResponse<StateEnum>(StateEnum.SUCCESS_OPTION);
	}

}
