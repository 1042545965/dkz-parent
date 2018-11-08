package com.atgugui.consumer;

import com.atgugui.enums.exceptionals.StateEnum;
import com.atgugui.model.blog.BlogListBox;
import com.atgugui.result.RestResponse;

public interface BlogService {

	RestResponse<StateEnum> insertBlog(BlogListBox blogListBox, String token);
	
}
