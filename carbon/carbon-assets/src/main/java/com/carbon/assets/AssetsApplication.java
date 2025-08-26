package com.carbon.assets;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.elasticsearch.client.RestHighLevelClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

/**
 * @author : Li Jun
 * @since : 2021-06-12 08:52
 **/
@SpringBootApplication(scanBasePackages = "com.carbon",exclude ={ SecurityAutoConfiguration.class} )
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.carbon")
@MapperScan({"com.carbon.*.mapper"})
public class AssetsApplication {

    /**
     *  启动方法
     */
    public static void main(String[] args) {
        SpringApplication.run(AssetsApplication.class,args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }

//    @Bean(destroyMethod = "close")
//    public RestHighLevelClient restClient() {
//
//        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                .connectedTo("127.0.0.1" +":" + "9200")
//                .build();
//        RestHighLevelClient client = RestClients.create(clientConfiguration).rest();
//        return client;
//
//    }

}
