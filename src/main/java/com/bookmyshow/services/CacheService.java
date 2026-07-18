package com.bookmyshow.services;

import org.springframework.stereotype.Service;

@Service
public interface CacheService {
    void set(String key, Object value);

    Object get(String key);

    void delete(String key);

    void getAllKeysAndValues();

}
