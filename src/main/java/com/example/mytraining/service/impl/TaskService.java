package com.example.mytraining.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class TaskService {

    @Resource
    private RedisTemplate<String, BigDecimal> redisTemplate;

    @Async
    public void startTask(String key) {
        BigDecimal start = BigDecimal.ZERO;
        BigDecimal end = new BigDecimal(100);
        while (start.compareTo(end) < 0) {
            int value = new Random().nextInt(10);
            start = start.add(new BigDecimal(value));
            if (start.compareTo(end) >= 0) {
                redisTemplate.opsForValue().set(key, end);
                log.info("{} 进度 >>> {}", key, 100);
            } else {
                redisTemplate.opsForValue().set(key, start);
                log.info("{} 进度 >>> {}", key, start);
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
