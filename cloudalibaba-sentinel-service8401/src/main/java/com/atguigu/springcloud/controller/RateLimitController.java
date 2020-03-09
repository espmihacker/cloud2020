package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.MyHandler;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @RequestMapping(value = "/byResource", method = RequestMethod.GET)
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult<Payment> byResource() {
        return new CommonResult<>(200, "按照资源名称限流成功！", new Payment(200L, "serial001"));
    }

    public CommonResult handleException(BlockException exception) {
        return new CommonResult(444, exception.getClass().getCanonicalName() + "\t服务不可用！");
    }

    @RequestMapping(value = "/rateLimit/byUrl", method = RequestMethod.GET)
    @SentinelResource(value = "byUrl")
    public CommonResult<Payment> byUrl() {
        return new CommonResult<>(200, "按照Url限流成功！", new Payment(200L, "serial002"));
    }

    //CustomBlockHandler
    @RequestMapping(value = "/rateLimit/customBlock", method = RequestMethod.GET)
    @SentinelResource(value = "customBlock", blockHandlerClass = MyHandler.class, blockHandler = "handlerException2")
    public CommonResult<Payment> customBlockHandler() {
        return new CommonResult<>(200, "按照客户自定义限流成功！", new Payment(200L, "serial003"));
    }

}
