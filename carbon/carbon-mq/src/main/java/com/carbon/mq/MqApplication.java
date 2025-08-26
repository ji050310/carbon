package com.carbon.mq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author : Li Jun
 * @since : 2021-06-12 08:52
 **/
@SpringBootApplication(scanBasePackages = "com.carbon",exclude ={ SecurityAutoConfiguration.class} )
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.carbon")
@MapperScan({"com.carbon.*.mapper"})
@EntityScan(basePackages = {"com.carbon.*"})
@ComponentScan(basePackages = {"com.carbon.common.*","com.carbon.*","com.carbon.mq.*"})
public class MqApplication {
    /**
     * 启动方法
     */
    public static void main(String[] args) {
        SpringApplication.run(MqApplication.class,args);
    }
}
