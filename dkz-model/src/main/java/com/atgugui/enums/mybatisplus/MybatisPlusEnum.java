package com.atgugui.enums.mybatisplus;

/**
 * 定义mybatisplus的map查询的表字段以及需要注入给常量的值
 * 
 * @author dkzadmin
 *
 */
public enum MybatisPlusEnum
{
	/*
	 * blog_list_box
	 * 博客列表
	 */
	BLOG_LIST_BOX_DELETE_STATUS("delete_status", "1"), 
    DISABLE("1", "停用"),
    DELETED("2", "删除") , 
    USER_TYPE("100","客户");

    private final String code;
    private final String info;

    MybatisPlusEnum(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
