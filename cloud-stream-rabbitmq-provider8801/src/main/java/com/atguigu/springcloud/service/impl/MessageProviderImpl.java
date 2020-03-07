package com.atguigu.springcloud.service.impl;

import org.springframework.cloud.stream.messaging.Source;
import com.atguigu.springcloud.service.IMessageProvider;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

//@Service  //这里不需要写这个注解
@EnableBinding(Source.class) //定义生产者的消息推送管道
public class MessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output;  //消息发送管道

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("serial = " + serial);
        return serial;
    }

}
