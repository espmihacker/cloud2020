package com.atguigu.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderZkController {

    public static final String INVOKE_URL = "http://cloud-provider-payment"; //cloud-provider-payment
    @Resource
    private RestTemplate restTemplate;


    /**
     * http://localhost/consumer/payment/zk
     *
     * @return
     */
    @RequestMapping(value = "/consumer/payment/zk", method = RequestMethod.GET)
    public String paymentInfo() {
        return restTemplate.getForObject(INVOKE_URL + "/payment/zk", String.class);
    }
}
