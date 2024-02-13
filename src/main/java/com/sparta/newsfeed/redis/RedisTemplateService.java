package com.sparta.newsfeed.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class RedisTemplateService {

    private final RedisTemplate<String, String> redisTemplate;

    public void save(String email, String token, long expiration) {
        redisTemplate.opsForValue().set(email, token, expiration, TimeUnit.MILLISECONDS);
    }

    public String get(String email) {
        return redisTemplate.opsForValue().get(email);
    }
}
