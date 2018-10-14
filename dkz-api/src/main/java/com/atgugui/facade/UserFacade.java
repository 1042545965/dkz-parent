package com.atgugui.facade;

import com.atgugui.model.user.BizUser;

public interface UserFacade {

	BizUser getUserByUser(BizUser bizUser);

	int updateBizUser(BizUser bizUser);

	int insertBizUser(BizUser bizUser);
	
}
