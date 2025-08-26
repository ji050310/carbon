package com.carbon.trade;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * @author : Li Jun
 * @since : 2021-06-12 08:52
 **/
@SpringBootApplication(scanBasePackages = "com.carbon",exclude ={ SecurityAutoConfiguration.class} )
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.carbon")
@MapperScan({"com.carbon.*.mapper"})
public class TradeApplication {

    /**
     * 启动方法
     */
    public static void main(String[] args) {

        SpringApplication.run(TradeApplication.class,args);
    }
}
