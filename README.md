# dkz-parent 项目结构,以及结构思路
>dkz-parent  
>>dkz-api

>>dkz-model --实体

>>>com.atgugui.enums --枚举模块

>>>com.atgugui.exceptions -- 自定义异常模块

>>>com.atgugui.model -- 实体类

>>>com.atgugui.result -- 自定义返回实体

>>dkz-provide
>>>jdbc --进行数据访问

>>>service --进行基础的数据封装,并且提供服务

>>dkz-server
>>>cache -- 缓存处理基类 , 消费服务 , 由consumer进行业务处理的时候进行调用,应为spring的注解缓存是基于aop的,  所以需要分开调用  

>>>consumer -- 消费服务,并且进行业务逻辑处理

>>>web --基础的网关权健
