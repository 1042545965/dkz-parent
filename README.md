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
>>>consumer -- 消费服务,并且进行业务逻辑处理

>>>web --基础的网关权健
