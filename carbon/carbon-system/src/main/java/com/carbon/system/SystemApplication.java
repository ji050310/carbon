package com.carbon.system;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.carbon.system.config.LoggingRequestInterceptor;
import org.elasticsearch.client.RestHighLevelClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author : Li Jun
 * @since : 2021-06-12 08:52
 **/
@SpringBootApplication(scanBasePackages = "com.carbon")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.carbon")
@MapperScan({"com.carbon.*.mapper"})
public class SystemApplication {
    /**
     * 启动方法
     */
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class,args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }

    @Bean(destroyMethod = "close")
    public RestHighLevelClient restClient() {

        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("127.0.0.1" +":" + "9200")
                .build();
        RestHighLevelClient client = RestClients.create(clientConfiguration).rest();
        return client;

    }

    @Bean
    public RestTemplate restTemplate(){
        ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.setInterceptors(Collections.singletonList(new LoggingRequestInterceptor()));
        return restTemplate;
    }
}
