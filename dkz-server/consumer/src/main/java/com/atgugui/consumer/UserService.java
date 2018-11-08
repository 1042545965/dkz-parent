package com.atgugui.consumer;

import com.atgugui.enums.exceptionals.StateEnum;
import com.atgugui.model.user.BizUser;
import com.atgugui.result.RestResponse;
import com.atgugui.vo.BizUserVo;

public interface UserService {

	RestResponse<BizUserVo> userLogin(String userName, String password);

	RestResponse<BizUser> userRegister(BizUser bizUser);

	RestResponse<StateEnum> getValidateCode(String userPhone);

}
