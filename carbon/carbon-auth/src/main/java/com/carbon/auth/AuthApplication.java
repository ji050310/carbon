package com.carbon.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : Li Jun
 * @since : 2021-06-12 08:52
 */
//@NacosPropertySource(dataId = "com.carbon.auth", autoRefreshed = true, groupId="carbon")
@SpringBootApplication(scanBasePackages = "com.carbon")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.carbon")
@MapperScan({"com.carbon.*.mapper"})
public class AuthApplication {
    /**
     * 启动方法
     */
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class,args);
    }
}
