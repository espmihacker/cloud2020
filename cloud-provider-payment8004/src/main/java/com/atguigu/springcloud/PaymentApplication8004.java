package com.atguigu.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient  // 该注解用于向consul或者zookeeper作为注册中心时注册服务
public class PaymentApplication8004 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication8004.class, args);
    }

}
