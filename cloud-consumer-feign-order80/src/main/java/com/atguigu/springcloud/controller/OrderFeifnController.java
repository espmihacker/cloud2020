package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeifnController {


    @Resource
    private PaymentFeignService paymentFeignService;

    @RequestMapping(value = "/consumer/payment/get/{id}", method = RequestMethod.GET)
    public CommonResult getPaymenyById(@PathVariable("id")Long id) {

        return paymentFeignService.getPaymenyById(id);
    }

    @RequestMapping(value = "/consumer/payment/timeout", method = RequestMethod.GET)
    public String paymentTimeout() {
        return paymentFeignService.paymentTimeout();
    }

}
