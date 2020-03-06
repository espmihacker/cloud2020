package com.atguigu.springcloud.service;


import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(value = "cloud-payment-service")
public interface PaymentFeignService {

    @RequestMapping(value = "/payment/get/{id}", method = RequestMethod.GET)
    CommonResult getPaymenyById(@PathVariable("id")Long id);

    @GetMapping(value = "/payment/timeout")
    String paymentTimeout();

}
