package com.sparta.newsfeed.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class RedisTemplateService {

    private final RedisTemplate<String, String> redisTemplate;

    public void save(String email, String token, long expiration) {
        long expirationTime = expiration - new Date().getTime();
        if (expirationTime <= 0) {
            throw new IllegalArgumentException("만료된 토큰입니다");
        }
        redisTemplate.opsForValue().set(email, token, expirationTime, TimeUnit.MILLISECONDS);
    }

    public String get(String email) {
        return redisTemplate.opsForValue().get(email);
    }
}
