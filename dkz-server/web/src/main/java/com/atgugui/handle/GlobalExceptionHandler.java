package com.atgugui.handle;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atgugui.enums.exceptionals.StateEnum;
import com.atgugui.exceptions.BaseException;
import com.atgugui.exceptions.user.UserException;
import com.atgugui.result.BaseResponse;


/**
 * @author dkzadmin
 * 全局异常处理
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7224191744147481301L;
	
	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 500- 业务异常
     */
    @ExceptionHandler(BaseException.class)
    public BaseResponse baseExceptionHandler(HttpServletResponse response, BaseException ex) {
        logger.error(ex.getMessage(), ex);
        return new BaseResponse(ex.getStateEnum().getCode(), ex.getMessage());
    }

    /**
     * 500- server error
     */
    @ExceptionHandler(Exception.class)
    public BaseResponse otherExceptionHandler(HttpServletResponse response, Exception ex) {
        logger.error(ex.getMessage(), ex);
        return new BaseResponse(StateEnum.ERROR_SYSTEM.getCode(), StateEnum.ERROR_SYSTEM.getMessage());
    }

    /**用户部分异常抛出 40000 - 60000
     * 500- server error
     */
    @ExceptionHandler(UserException.class)
    public BaseResponse userExceptionHandler(HttpServletResponse response, UserException ex) {
        logger.error(ex.getMessage(), ex);
        return new BaseResponse(ex.getuserExceptionEnum().getCode(), ex.getuserExceptionEnum().getMessage());
    }
    
    /**
     * 缺少请求参数- Bad Request
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public BaseResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        logger.error(ex.getMessage(), ex);
        return new BaseResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    /**
     * 参数解析失败- Bad Request
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public BaseResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        logger.error(ex.getMessage(), ex);
        return new BaseResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    /**
     * 参数验证失败 - Bad Request
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        logger.error(ex.getMessage(), ex);
        return new BaseResponse(HttpStatus.BAD_REQUEST.value(), ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 参数绑定失败- Bad Request
     */
    @ExceptionHandler(BindException.class)
    public BaseResponse handleBindException(BindException ex) {
        logger.error(ex.getMessage(), ex);
        return new BaseResponse(HttpStatus.BAD_REQUEST.value(), ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 参数验证失败 - Bad Request
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResponse handleServiceException(ConstraintViolationException ex) {
        logger.error(ex.getMessage(), ex);
        return new BaseResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    /**
     * 参数验证失败 - Bad Request
     */
    @ExceptionHandler(ValidationException.class)
    public BaseResponse handleValidationException(ValidationException ex) {
        logger.error(ex.getMessage(), ex);
        return new BaseResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }


    /**
     * 415 - 媒体类型不匹配
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public BaseResponse handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        return new BaseResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), ex.getMessage());
    }

    /**
     * 405 - 请求方法不匹配
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public BaseResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return new BaseResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), ex.getMessage());
    }
}
