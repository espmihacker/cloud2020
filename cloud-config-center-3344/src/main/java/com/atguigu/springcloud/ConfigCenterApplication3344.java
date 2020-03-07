package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


// 通知ALL POST http://localhost:3344/actuator/bus-refresh

// 只通知3355，不通知3366 POST http://localhost:3344/actuator/bus-refresh/config-client:3355

@SpringBootApplication
@EnableConfigServer
public class ConfigCenterApplication3344 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterApplication3344.class, args);
    }

}
