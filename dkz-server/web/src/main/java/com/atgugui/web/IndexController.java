package com.atgugui.web;
 

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atgugui.common.utils.ServletUtils;
import com.atgugui.consumer.HomePagetaTitleService;
import com.atgugui.consumer.UserService;
import com.atgugui.enums.exceptionals.StateEnum;
import com.atgugui.enums.user.UserStatus;
import com.atgugui.model.user.BizUser;
import com.atgugui.result.RestResponse;
import com.atgugui.vo.BizUserVo;
import com.atgugui.vo.TitleVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

 
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
        @ApiImplicitParam(name = "password", value = "用户密码", required = true ,dataType = "string"),
        @ApiImplicitParam(name = "remark", value = "备注", required = true ,dataType = "string")
	})
    @PostMapping("/userRegister")
    public RestResponse<BizUser> userRegister(BizUser bizUser){
		bizUser.setUserName(bizUser.getPhonenumber());
		bizUser.setLoginDate(new Date());
		bizUser.setLoginIp(ServletUtils.getIP());
		bizUser.setStatus(UserStatus.OK.getCode());
		bizUser.setUserType(UserStatus.USER_TYPE.getCode());
		bizUser.setCreateTime(new Date());
		bizUser.setUpdateTime(new Date());
		return userService.userRegister(bizUser);
    }
    
    
    
	/** 用户登录 , 返回token
	 * @param userName
	 * @param password
	 * @return 
	 */
	@ApiOperation(value = "用户登录接口",notes = "登录")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "userName", value = "用户名" , required = true ,dataType = "string"),
        @ApiImplicitParam(name = "password", value = "密码", required = true ,dataType = "string")
	})
    @PostMapping("/userLogin")
    public RestResponse<BizUserVo> userLogin(@RequestParam("userName") String userName ,  @RequestParam("password") String password){
			return userService.userLogin(userName , password);
    }
	
	/**直接使用post发送请求就可以了 , post作为新增的方法
	 * 仔细去了解了一下 restful的api put 和delete 还需要去设置 , 暂时不倒腾.
	 * @param userPhone
	 * @return
	 */
	@ApiOperation(value = "获取验证码",notes = "验证码")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "userPhone", value = "手机号码" , required = true ,dataType = "string")
	})
    @PostMapping("/validateCode/{userPhone}")
    public RestResponse<StateEnum> validateCode(@PathVariable String userPhone){
			return userService.getValidateCode(userPhone);
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
