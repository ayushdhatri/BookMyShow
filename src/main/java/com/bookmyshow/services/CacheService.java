package com.bookmyshow.services;

import org.springframework.stereotype.Service;

@Service
public interface CacheService {
    void save(String key, Object value);

    Object get(String key);

    void delete(String key);
}
