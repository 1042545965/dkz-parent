package com.atgugui.facade;

import com.atgugui.model.Notify;
import com.atgugui.model.operlog.SysOperLog;

public interface AsyncFacade {

	Integer insertNotify(Notify notify2);

	Integer insertSysOperLog(SysOperLog operLog);

}
