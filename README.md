# spring-redis-cache
Demo application using distributed cache with Redis and Spring Boot

## Cache Annotations

### @EnableCaching

Enables Spring's caching support.

### @Cacheable

- Checks whether data exists in cache.
- Executes the method only on a cache miss.
- Stores the returned value in the cache.

### @CachePut

- Always executes the method.
- Updates both the database and the cache.

### @CacheEvict

- Removes cache entries after data deletion or invalidation.

## Redis Configuration

```java
@Bean
public RedisCacheConfiguration cacheConfiguration() {
    return RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(10));
}
```

### TTL (Time To Live)

Cache entries expire automatically after 10 minutes. Once expired, the next request fetches fresh data from the database and repopulates the cache.
