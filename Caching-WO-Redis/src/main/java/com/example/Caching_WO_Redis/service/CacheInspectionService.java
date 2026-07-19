package com.example.Caching_WO_Redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CacheInspectionService {

    @Autowired
    private CacheManager cacheManager;

    public void getCachedata(String cacheName){
       Cache cache = cacheManager.getCache(cacheName);
       if(cache != null){
           System.out.println("Cache content: ");
           System.out.println(Objects.requireNonNull(cache.getNativeCache().toString()));
       }
       else{
           System.out.println("No cache data available");
       }
    }
}
