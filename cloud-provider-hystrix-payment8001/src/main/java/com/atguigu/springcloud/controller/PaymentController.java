package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    /**
     * 正常
     * @param id
     * @return
     */
    @RequestMapping(value = "/payment/hystrix/ok/{id}", method = RequestMethod.GET)
    public String paymentInfo_OK(@PathVariable Integer id) {
        String result = paymentService.PaymentInfo_OK(id);
        log.info("***************" + result);

        return result;
    }

    /**
     * 超时
     * @param id
     * @return
     */
    @RequestMapping(value = "/payment/hystrix/timeout/{id}", method = RequestMethod.GET)
    public String paymentInfo_Timeout(@PathVariable Integer id) {
        String result = paymentService.paymentInfo_Timeout(id);
        log.info("***************" + result);

        return result;
    }

    // ===============================================服务熔断===========================================================
    @RequestMapping(value = "/payment/hystrix/circuit/{id}", method = RequestMethod.GET)
    public String paymentCircuitBreaker(@PathVariable("id")Integer id) {
        String result = paymentService.paymentCircuitBreaker(id);

        return result;
    }

}
