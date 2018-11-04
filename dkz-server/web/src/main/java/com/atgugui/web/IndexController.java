package com.atgugui.web;
 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atgugui.common.utils.LogUtils;
import com.atgugui.consumer.HomePagetaTitleService;
import com.atgugui.consumer.UserService;
import com.atgugui.enums.exceptionals.StateEnum;
import com.atgugui.exceptions.user.UserException;
import com.atgugui.model.user.BizUser;
import com.atgugui.result.BaseResult;
import com.atgugui.result.RestResponse;
import com.atgugui.vo.TitleVO;

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
    @Autowired
    private HomePagetaTitleService HomePagetaTitleService;
	
    
    
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
	
	/**直接使用post发送请求就可以了 , post作为新增的方法
	 * 仔细去了解了一下 restful的api put 和delete 还需要去设置 , 暂时不倒腾.
	 * @param userPhone
	 * @return
	 */
	@ApiOperation(value = "获取验证码",notes = "验证码")
    @PostMapping("/validateCode/{userPhone}")
    public BaseResult validateCode(@ApiParam(name = "userPhone", value = "手机号码") @PathVariable String userPhone){
		try {
			return userService.getValidateCode(userPhone);
		} catch (UserException e) {
			return BaseResult.newFailed(e.getuserExceptionEnum().getCode() , e.getuserExceptionEnum().getMessage());
		}catch (Exception e) {
			//这里可以有一个日志工具类
			LogUtils.logError(e.getMessage(), e);
			return BaseResult.newFailed(StateEnum.ERROR_SYSTEM);
		}
    }
    
	
	/**直接使用post发送请求就可以了 , post作为新增的方法
	 * 仔细去了解了一下 restful的api put 和delete 还需要去设置 , 暂时不倒腾.
	 * @param userPhone
	 * @return
	 */
	@ApiOperation(value = "获取首页表头",notes = "首页表头")
	 @GetMapping("/homePagetaTitle")
    public RestResponse<List<TitleVO>> homePagetaTitle(){
		return HomePagetaTitleService.getHomePagetaTitle();
    }
}
