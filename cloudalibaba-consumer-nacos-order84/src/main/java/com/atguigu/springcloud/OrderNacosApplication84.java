package com.atguigu.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients     //启用feign支持
public class OrderNacosApplication84 {

    public static void main(String[] args) {
        SpringApplication.run(OrderNacosApplication84.class, args);
    }

}
