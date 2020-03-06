package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/payment/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);

        log.info("插入结果：" + result);

        if (result > 0) {
            return new CommonResult(200, "插入成功", result);
        } else {
            return new CommonResult(444, "插入失败", null);
        }
    }

    @RequestMapping(value = "/payment/get/{id}", method = RequestMethod.GET)
    public CommonResult getPaymenyById(@PathVariable("id")Long id) {
        Payment payment = paymentService.getPaymentById(id);

        log.info("查询结果：" + payment + serverPort);

        if (payment != null) {
            return new CommonResult(200, "查询成功" + serverPort, payment);
        } else {
            return new CommonResult(444, "查询失败" + serverPort, null);
        }
    }

    @RequestMapping(value = "/payment/discovery", method = RequestMethod.GET)
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("****ele: " + service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("****instances: " + instance.getHost() + " - " + instance.getPort() + " - " + instance.getUri());
        }

        return discoveryClient;
    }

    @RequestMapping(value = "/payment/lb", method = RequestMethod.GET)
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping(value = "/payment/timeout")
    public String paymentTimeout() {

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
