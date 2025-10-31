package uz.brb.redis_test.service;

import org.springframework.data.domain.Pageable;
import uz.brb.redis_test.dto.response.Response;
import uz.brb.redis_test.entity.RedisTest;

public interface RedisTestService {
    Response<?> add(RedisTest redisTest);

    Response<?> get(Long id);

    Response<?> getAll(Pageable pageable);
}
