package com.bookmyshow.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service

public class RedisService implements CacheService{
    private final StringRedisTemplate stringRedisTemplate;

    public RedisService(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void set(String key, Object value) {
        stringRedisTemplate.opsForValue().set(key, value.toString());
    }

    @Override
    public Object get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        stringRedisTemplate.delete(key);
        }

    @Override
    public void getAllKeysAndValues() {
        stringRedisTemplate.keys("*").forEach(key -> {
            System.out.println();
            System.out.print("Key " + key + " value: ");
            System.out.println(stringRedisTemplate.opsForValue().get(key));
        });

    }
}
