package com.atgugui.web;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atgugui.common.utils.LogUtils;
import com.atgugui.consumer.UserService;
import com.atgugui.enums.exceptionals.StateEnum;
import com.atgugui.exceptions.user.UserException;
import com.atgugui.model.user.BizUser;
import com.atgugui.result.BaseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

 
/**
 * @author conlon
 *
 */
@Api("不被拦截")
@RestController
public class IndexController {

    @Autowired
    private UserService userService;
	
    
    
	@ApiOperation(value = "用户注册接口",notes = "注册")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "loginName", value = "登录账号(默认为手机号码)" , required = true ,dataType = "string"),
        @ApiImplicitParam(name = "email", value = "用户邮箱", required = true ,dataType = "string"),
        @ApiImplicitParam(name = "phonenumber", value = "手机号码", required = true ,dataType = "string"),
        @ApiImplicitParam(name = "sex", value = "用户性别（0男 1女 2未知）", required = true ,dataType = "string"),
        @ApiImplicitParam(name = "password", value = "用户密码", required = true ,dataType = "string"),
        @ApiImplicitParam(name = "remark", value = "备注", required = true ,dataType = "string")
	})
    @GetMapping("/userRegister")
    public BaseResult userRegister(BizUser bizUser){
		try {
			return userService.userRegister(bizUser);
		} catch (UserException e) {
			return BaseResult.newFailed(e.getuserExceptionEnum().getCode() , e.getuserExceptionEnum().getMessage());
		}catch (Exception e) {
			//这里可以有一个日志工具类
			LogUtils.logError(e.getMessage(), e);
			return BaseResult.newFailed(StateEnum.ERROR_SYSTEM);
		}
    }
    
    
    
	/** 用户登录
	 * @param userName
	 * @param password
	 * @return 
	 */
	@ApiOperation(value = "用户登录接口",notes = "登录")
    @GetMapping("/userLogin")
    public BaseResult userLogin(@ApiParam(name = "userName", value = "用户名") String userName , 
    						@ApiParam(name = "password", value = "密码") String password){
		try {
			return userService.userLogin(userName , password);
		} catch (UserException e) {
			//这里的日志是用来抛出的
			return BaseResult.newFailed(e.getuserExceptionEnum().getCode() , e.getuserExceptionEnum().getMessage());
		}catch (Exception e) {
			//这里可以有一个日志工具类
			LogUtils.logError(e.getMessage(), e);
			return BaseResult.newFailed(StateEnum.ERROR_SYSTEM);
		}
    }
    
}
