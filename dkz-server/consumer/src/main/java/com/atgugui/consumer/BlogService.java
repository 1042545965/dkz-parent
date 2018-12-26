package com.atgugui.consumer;

import java.util.List;

import com.atgugui.enums.exceptionals.StateEnum;
import com.atgugui.model.blog.BlogListBox;
import com.atgugui.result.RestResponse;

public interface BlogService {

	RestResponse<StateEnum> insertBlog(BlogListBox blogListBox, String token);

	RestResponse<List<BlogListBox>> getBlogList(BlogListBox blogListBox , Integer page , Integer pageSize);
	
}
