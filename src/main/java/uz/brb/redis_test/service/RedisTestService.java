package uz.brb.redis_test.service;

import org.springframework.data.domain.Pageable;
import uz.brb.redis_test.dto.response.Response;

public interface RedisTestService {
    Response<?> getAll(Pageable pageable);
}
