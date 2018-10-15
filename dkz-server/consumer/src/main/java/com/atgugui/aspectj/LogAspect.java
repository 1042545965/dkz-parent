package com.atgugui.aspectj;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.atgugui.annotation.Log;
import com.atgugui.common.utils.AppUtil;
import com.atgugui.common.utils.IpUtils;
import com.atgugui.common.utils.ServletUtils;
import com.atgugui.common.utils.StringUtils;
import com.atgugui.enums.annotation.BusinessStatus;
import com.atgugui.facade.AsyncFacade;
import com.atgugui.model.operlog.SysOperLog;



@Aspect
@Component
public class LogAspect {

	 /**
	 *  打印日志
	 */
	private static final Logger log = LoggerFactory.getLogger(LogAspect.class);
	
	@Reference
	private AsyncFacade asyncFacade;
	
	 // 配置织入点
    @Pointcut("@annotation(com.atgugui.annotation.Log)")
    public void logPointCut()
    {
    }
    
    /**
     * 前置通知 用于拦截操作 , 在方法执行之后调用
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()")
    public void doBefore(JoinPoint joinPoint)
    {
    		 handleLog(joinPoint, null);
    }
    
    /**
     * 拦截异常操作
     * 
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfter(JoinPoint joinPoint, Exception e)
    {
        handleLog(joinPoint, e);
    }
    
    /**
     * @param joinPoint
     * @param e
     */
    protected void handleLog(final JoinPoint joinPoint, final Exception e)
    {
        try
        {
        	Log controllerLog = getAnnotationLog(joinPoint);
			//System.out.println("title = "+controllerLog.title() + "businessType" + controllerLog.businessType());
			if (AppUtil.isNull(controllerLog))
            {
                return;
            }	
			SysOperLog operLog = new SysOperLog();
			// 请求的地址
            String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
            operLog.setOperIp(ip);
            //获取请求的url	
            operLog.setOperUrl(ServletUtils.getRequest().getRequestURI());

            if (e != null)
            {
            	//
                operLog.setStatus(BusinessStatus.FAIL.ordinal());
                operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operLog.setMethod(className + "." + methodName + "()");
            // 处理设置注解上的参数
            operLog = getControllerMethodDescription(controllerLog, operLog);
            // 保存数据库
            //AsyncManager.me().execute(AsyncFactory.recordOper(operLog));
            operLog.setOperTime(new Date());
            //asyncService.insertSysOperLog(operLog);
            asyncFacade.insertSysOperLog(operLog);
        }
        catch (Exception exp)
        {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }
    
    
    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     * 
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public SysOperLog getControllerMethodDescription(Log log, SysOperLog operLog) throws Exception
    {
        // 设置action动作
        operLog.setBusinessType(log.businessType().ordinal());
        // 设置标题
        operLog.setTitle(log.title());
        // 设置操作人类别
        operLog.setOperatorType(log.operatorType().ordinal());
        // 是否需要保存request，参数和值
        if (log.isSaveRequestData())
        {
            // 获取参数的信息，传入到数据库中。
        	operLog = setRequestValue(operLog);
        }
		return operLog;
    }

    /**
     * 获取请求的参数，放到log中
     * 
     * @param operLog
     * @param request
     */
    private SysOperLog setRequestValue(SysOperLog operLog)
    {
        Map<String, String[]> map = ServletUtils.getRequest().getParameterMap();
        String params = JSONObject.toJSONString(map);
        operLog.setOperParam(StringUtils.substring(params, 0, 255));
		return operLog;
    }
    
    
    /**
     * 是否存在注解，如果存在就获取
     */
    private Log getAnnotationLog(JoinPoint joinPoint) throws Exception
    {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null)
        {
            return method.getAnnotation(Log.class);
        }
        return null;
    }
}
