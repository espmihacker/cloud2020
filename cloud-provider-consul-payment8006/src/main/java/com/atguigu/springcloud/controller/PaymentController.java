package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    /**
     * http://localhost:8006/payment/zk
     *
     * @return
     */
    @RequestMapping(value = "/payment/consul", method = RequestMethod.GET)
    public String paymentZk() {
        return "SpringCloud with consul:" + serverPort + "\t" + UUID.randomUUID().toString();
    }
}

