package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(value = "cloud-provider-hystrix-payment", fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {

    @RequestMapping(value = "/payment/hystrix/ok/{id}", method = RequestMethod.GET)
    String paymentInfo_OK(@PathVariable(value = "id") Integer id);

    @RequestMapping(value = "/payment/hystrix/timeout/{id}", method = RequestMethod.GET)
    String paymentInfo_Timeout(@PathVariable(value = "id") Integer id);

}
