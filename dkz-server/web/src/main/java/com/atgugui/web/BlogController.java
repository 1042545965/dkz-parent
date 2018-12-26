package com.atgugui.web;
 

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atgugui.common.utils.IpUtils;
import com.atgugui.consumer.BlogService;
import com.atgugui.enums.exceptionals.StateEnum;
import com.atgugui.model.blog.BlogListBox;
import com.atgugui.result.RestResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

 
///**
// * @author zhuzhe
// * @date 2018/5/25 16:00
// * @email 1529949535@qq.com
// */
@Api("博客控制层")
@RestController("/blog")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@ApiOperation(value = "保存博客接口",notes = "登录")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "blogName", value = "博客名称" , required = true ,dataType = "string"),
        @ApiImplicitParam(name = "blogDigest", value = "博客内容", required = true ,dataType = "string"),
        @ApiImplicitParam(name = "blogType", value = "博客类型", required = true ,dataType = "string")
	})
    @PostMapping("/insertBlog")
    public RestResponse<StateEnum> insertBlog(BlogListBox blogListBox , @RequestParam String token , HttpServletRequest request){
		blogListBox.setCreateIp(IpUtils.getIpAddr(request));
		blogListBox.setCreateTime(new Date());
		blogListBox.setUpdateTime(new Date());
		return blogService.insertBlog(blogListBox , token);
    }
	
	
	@ApiOperation(value = "展示博客列表",notes = "博客列表")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "blogName", value = "博客名称" , required = false ,dataType = "string"),
        @ApiImplicitParam(name = "blogType", value = "博客类型", required = false ,dataType = "string"),
        @ApiImplicitParam(name = "page", value = "当前页", required = false ,dataType = "string"),
        @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false ,dataType = "string")
	})
    @PostMapping("/getBlogList")
    public RestResponse<List<BlogListBox>> getBlogList(BlogListBox blogListBox , 
    		@RequestParam(value="page", required=false, defaultValue= "1")Integer page , 
    		@RequestParam(value="pageSize", required=false, defaultValue= "10")Integer pageSize){
		return blogService.getBlogList(blogListBox, page, pageSize);
    }
	
}
