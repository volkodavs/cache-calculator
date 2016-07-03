package com.sergeyvolkodav.cachecalculator.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CalcCacheRepository {


    @Autowired
    private RedisTemplate<String, Object> template;

    public String getCachedResult(String key) {
        Object o = template.opsForValue().get(key);
        if (o != null) {
            return o.toString();
        }
        return null;
    }

    public void saveCachedResult(String key, String calcResult) {
        template.opsForValue().set(key, calcResult);
    }
}
