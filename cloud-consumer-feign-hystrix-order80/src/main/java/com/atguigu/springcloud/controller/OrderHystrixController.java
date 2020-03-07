package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "orderGlobalFallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @HystrixCommand
    @RequestMapping(value = "/consumer/payment/hystrix/ok/{id}", method = RequestMethod.GET)
    public String paymentInfo_OK(@PathVariable(value = "id") Integer id){
        //int a = 10 / 0;
        String result = paymentHystrixService.paymentInfo_OK(id);

        return result;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })
    @RequestMapping(value = "/consumer/payment/hystrix/timeout/{id}", method = RequestMethod.GET)
    public String paymentInfo_Timeout(@PathVariable(value = "id") Integer id) {
        String result = paymentHystrixService.paymentInfo_Timeout(id);

        return result;
    }

    public String paymentInfo_TimeoutHandler(@PathVariable(value = "id") Integer id) {
        return "服务错误！" + id;
    }

    /**
     * 全局fallback方法
     * @return
     */
    public String orderGlobalFallbackMethod() {
        return "通用异常处理！！";
    }

}
