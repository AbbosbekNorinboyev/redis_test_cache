package uz.brb.redis_test.service.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisCacheService {
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String PREFIX = "redis_test:";

    public void saveData(String key, Object value) {
        redisTemplate.opsForValue().set(PREFIX + key, value);
    }

    public void saveData(String key, Object value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(PREFIX + key, value, timeout, timeUnit);
    }

    public Object getData(String key) {
        return redisTemplate.opsForValue().get(PREFIX + key);
    }

    public void removeData(String key) {
        redisTemplate.delete(PREFIX + key);
    }

    public Set<String> getAllKeys() {
        // redis_test: bilan boshlanadigan barcha keylarni olish
        return redisTemplate.keys(PREFIX + "*");
    }

    public List<Object> getAllData() {
        Set<String> keys = getAllKeys();
        if (keys == null || keys.isEmpty()) {
            return List.of();
        }

        // har bir key orqali value (data) olish
        return keys.stream()
                .map(redisTemplate.opsForValue()::get)
                .toList();
    }
}
