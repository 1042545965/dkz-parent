package com.atgugui.facade;

import java.util.List;
import java.util.Map;

import com.atgugui.model.blog.BlogListBox;

public interface BlogFacade {

	BlogListBox insertBlog(BlogListBox blogListBox, String info);

	List<BlogListBox> getBlogList(Map<String, Object> columnMap);
	
}
