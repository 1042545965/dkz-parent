package com.atgugui.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.atgugui.enums.exceptionals.StateEnum;
import com.atgugui.exceptions.BaseException;
import com.atgugui.facade.HelloFacade;
import com.atgugui.jdbc.EmployeeMapper;
import com.atgugui.model.Employee;

@Component
@Service
public class HelloFacadeImpl implements HelloFacade {

	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Override
	public String getStr() {
		Employee empById = employeeMapper.getEmpById(1);
		System.out.println(empById);
		return "this is dubbo , 哈哈哈看看是不是utf-8";
	}

	@Override
	@Transactional
	public Employee insert(Employee employeein) throws BaseException {
		employeein.getId().toString(); //测试provide全局异常
		employeeMapper.insert(employeein);
		if (true) {
			throw new BaseException(StateEnum.ERROR_TEST);//测试自定义异常
		}
		return employeein;
	}

	@Override
	public Employee selectCacheById(Integer id) {
		Employee empById = employeeMapper.getEmpById(id);
		return empById;
	}

}
