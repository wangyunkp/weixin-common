package com.cloud.weixin;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@Slf4j
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
public class WeiXinCoreMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeiXinCoreMainApplication.class, args);
        log.info("======================================================================");
        log.info("wei-xin-core service start success");
        log.info("======================================================================");
    }
}