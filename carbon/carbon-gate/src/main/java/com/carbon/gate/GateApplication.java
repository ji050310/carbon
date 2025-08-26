package com.carbon.gate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Li Jun
 * @since : 2021-06-12 08:52
 **/
@SpringBootApplication(scanBasePackages = "com.carbon", exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients(basePackages = "com.carbon")
@EnableDiscoveryClient
public class GateApplication {

    public static void main(String[] args) {
        SpringApplication.run(GateApplication.class);
    }
}
