package com.atgugui.facade;

import com.atgugui.exceptions.BaseException;
import com.atgugui.model.Employee;

public interface HelloFacade {
	
	public String getStr();

	public Employee insert(Employee employeein) throws BaseException;

	public Employee selectCacheById(Integer id);
	
	
}
