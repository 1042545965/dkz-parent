package com.atgugui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 1、引入依赖‘
 * 2、配置dubbo的注册中心地址
 * 3、引用服务	
 */
@SpringBootApplication
@EnableCaching //开启基于注解的缓存的必要操作
@EnableAsync
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}
}
