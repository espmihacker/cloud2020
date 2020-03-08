package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SentinelApplication8401 {

    public static void main(String[] args) {
        SpringApplication.run(SentinelApplication8401.class, args);
    }

}
