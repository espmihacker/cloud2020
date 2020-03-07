package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 自定义负载均衡路由规则类
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule() {
        System.out.println("======>RandomRule");
        // 定义为随机
        return new RandomRule();

        // return new RoundRobinRule(); //默认轮询
    }
}