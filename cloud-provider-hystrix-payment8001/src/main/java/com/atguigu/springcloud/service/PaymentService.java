package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String PaymentInfo_OK(Integer id) {
        return "线程池" + Thread.currentThread().getName() + "PaymentInfo_OK" + id + "\t" + "O(∩_∩)O哈哈~";
    }

    // ===============================================服务降级===========================================================

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_Timeout(Integer id) {
        // int a = 10 / 0;
        // 暂停几秒钟的线程
        try { TimeUnit.MILLISECONDS.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }
        return "线程池" + Thread.currentThread().getName() + "PaymentInfo_Timeout" + id + "\t" + "耗时秒~~";
    }

    public String paymentInfo_TimeoutHandler(Integer id) {
        return "线程池" + Thread.currentThread().getName() + "PaymentInfo_Timeout" + id + "\t" + "o(╥﹏╥)o";
    }

    // ===============================================服务熔断===========================================================

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),               //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),    //时间窗口
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")        //失败率多少次跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id")Integer id) {
        if(id < 0) {
            throw new RuntimeException("id不能是负数！");
        }

        String serial = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serial;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id")Integer id) {
        return "CircuitBreaker，请稍后再试~~~" + id;
    }

}
