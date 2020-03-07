package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalanced;
import com.atguigu.springcloud.lb.MyLb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    //private static final String PAYMENT_URL = "http://localhost:8001";
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalanced loadBalanced;

    @Resource
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/consumer/payment/create", method = RequestMethod.POST)
    public CommonResult create(Payment payment) {

        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @RequestMapping(value = "/consumer/payment/get/{id}", method = RequestMethod.GET)
    public CommonResult getPaymentById(@PathVariable("id")Long id) {

        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @RequestMapping(value = "/consumer/payment/getForEntity/{id}", method = RequestMethod.GET)
    public CommonResult getPaymenyById2(@PathVariable("id")Long id) {

        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);

        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommonResult<>(444, "查询失败！", null);
        }
    }

    /**
     * 自我实现负载均衡轮询算法 仿ribbon
     * @return
     */
    @RequestMapping(value = "/consumer/payment/lb", method = RequestMethod.GET)
    public String getPaymentLb() {
        List<ServiceInstance> services = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (services == null || services.size() <= 0) {
            return null;
        }

        ServiceInstance instance = loadBalanced.instance(services);
        URI uri = instance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }

    //==========================>zipkin
    @RequestMapping(value = "/consumer/payment/zipkin", method = RequestMethod.GET)
    public String paymentZipkin() {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/zipkin", String.class);
    }

}
