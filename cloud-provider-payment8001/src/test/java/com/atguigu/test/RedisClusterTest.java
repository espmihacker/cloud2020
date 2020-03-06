package com.atguigu.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisClusterTest.class)
public class RedisClusterTest {

    private RedisTemplate redisTemplate;

    @Test
    public void testAdd() {
        redisTemplate = new RedisTemplate();
        redisTemplate.boundValueOps("abc").set("uuuuuuu");
        redisTemplate.boundValueOps("qwe").set("xxxxxxx");
    }
}
