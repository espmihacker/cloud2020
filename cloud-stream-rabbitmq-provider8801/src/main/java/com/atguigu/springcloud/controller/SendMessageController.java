package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class SendMessageController {

    @Resource
    private IMessageProvider messageProvider;

    @RequestMapping(value = "/sendMessage", method = RequestMethod.GET)
    public String sendMessage(){
        return messageProvider.send();
    }

}
