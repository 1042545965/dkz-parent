package com.atgugui.web;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.atgugui.consumer.HelloService;
import com.atgugui.enums.exceptionals.StateEnum;
import com.atgugui.exceptions.BaseException;
import com.atgugui.model.Employee;
import com.atgugui.result.BaseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

 
///**
// * @author zhuzhe
// * @date 2018/5/25 16:00
// * @email 1529949535@qq.com
// */
@Api("用户控制层")
@RestController
public class UserController {

    

	
    /** 使用restful 风格的 get请求
     * @param id
     * @return 
     */
//    @GetMapping("/emp/{id}")
//    public BaseResult getEmp(@PathVariable("id") Integer id){
//       try {
//    	   Employee byCacheEmployee = helloService.getByCache(id);
//    	   return BaseResult.newSuccess(byCacheEmployee);
//	} catch (Exception e) {
//		return BaseResult.newFailed(StateEnum.ERROR_SYSTEM);
//	}
//    	
//    }
    
}
