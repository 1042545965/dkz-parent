# 欢迎来到dkz-parent
## 该项目基于springboot 整合了dubbo , mybatis , redis
### dubbo部分
#### dubbo 部分进行模块数据的基础封装 , 整合了mybatis mybatis-plus mybatis注解版本,提高代码可读性,以及开发效率,且不影响对原生mybatis的使用
### consumer部分
### 对provide进行消费,处理前端请求.同时集成了缓存部分.

## dkz-parent 项目结构图
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
>>>cache -- 缓存处理基类  

>>>>com.atgugui.cache --需要调用缓存的方法

>>>>com.atgugui.config -- 缓存基本配置,使用的是bean注入的配置方式

>>>consumer -- 消费服务,并且进行业务逻辑处理

>>>web --基础的网关权健
