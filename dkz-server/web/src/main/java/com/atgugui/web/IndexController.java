package com.atgugui.web;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.atgugui.consumer.HelloService;
import com.atgugui.consumer.UserService;
import com.atgugui.enums.exceptionals.StateEnum;
import com.atgugui.exceptions.BaseException;
import com.atgugui.exceptions.user.UserException;
import com.atgugui.model.Employee;
import com.atgugui.result.BaseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
			return BaseResult.newFailed(e.getuserExceptionEnum().getCode() , e.getuserExceptionEnum().getMessage());
		}catch (Exception e) {
			//这里可以有一个日志工具类
			System.out.println(e);
			return BaseResult.newFailed(StateEnum.ERROR_SYSTEM);
		}
    }
    
}
