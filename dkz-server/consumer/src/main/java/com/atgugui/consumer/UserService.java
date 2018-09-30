package com.atgugui.consumer;

import com.atgugui.result.BaseResult;

public interface UserService {

	BaseResult userLogin(String userName, String password);

}
