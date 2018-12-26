package com.atgugui.common.utils;

import java.util.Collections;
import java.util.List;

import com.google.common.base.Objects;


/**
 * @author dkzadmin
 *
 */
public final class ListUtil {
	
	
	
	   /** 分割list ,分页
	 * @param list
	 * @param page 当前页
	 * @param pageSize 每页多少条
	 * @return
	 */
	public static <T> List<T> getPageList(List<T> list, int page, int pageSize) {
	        if (AppUtil.isNull(list)) {
	            return Collections.emptyList();//空数组
	        }
	        int fromIndex = (page - 1) * pageSize;
	        if (fromIndex >= list.size()) {
	            return Collections.emptyList();
	        }
	        if (fromIndex < 0) {
	            return Collections.emptyList();
	        }
	        int toIndex = page * pageSize;
	        if (toIndex >= list.size()) {
	            toIndex = list.size();
	        }
	        return list.subList(fromIndex, toIndex);
	    }
}
