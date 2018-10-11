package com.atgugui.enums.exceptionals;

import java.util.HashMap;
import java.util.Map;


/**
 * @author dkzadmin
 * 全局基础异常的枚举
 * 异常码从 10000 开始 , 每个模块以一万递增 , 200 400 500 ,留用
 */
public enum StateEnum {
	SUCCESS_OPTION(200, "操作成功"),
	ERROR_PARAMETE(300, "参数异常"),
	
    ERROR_SYSTEM(10000, "系统异常"),
    
    ERROR_PHONE(20000, "手机号码异常"),
    
    ERROR_TEST(1000000, "测试异常");
    

    private Integer code;

    private String message;

    private StateEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     *将该枚举全部转化成map 对象
     * @return
     */
    public static  Map<Integer, String> getMap(){
        Map<Integer, String> map = new HashMap<Integer, String>();
        for (StateEnum e : StateEnum.values()) {
        	map.put(e.getCode(), e.getMessage());
        }
        return map;
    }
}
