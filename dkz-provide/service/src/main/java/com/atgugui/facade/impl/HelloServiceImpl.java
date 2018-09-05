package com.atgugui.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.atgugui.enums.exceptionals.StateEnum;
import com.atgugui.exceptions.BaseException;
import com.atgugui.facade.HelloService;
import com.atgugui.jdbc.EmployeeMapper;
import com.atgugui.model.Employee;

@Component
@Service
public class HelloServiceImpl implements HelloService {

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
		employeeMapper.insert(employeein);
		if (true) {
			throw new BaseException(StateEnum.ERROR_TEST);
		}
		return employeein;
	}

}
