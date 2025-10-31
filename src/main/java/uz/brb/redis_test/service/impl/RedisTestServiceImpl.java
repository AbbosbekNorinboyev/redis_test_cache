package uz.brb.redis_test.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.brb.redis_test.dto.response.Response;
import uz.brb.redis_test.entity.RedisTest;
import uz.brb.redis_test.repository.RedisTestRepository;
import uz.brb.redis_test.service.RedisTestService;
import uz.brb.redis_test.service.logic.RedisCacheService;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisTestServiceImpl implements RedisTestService {
    private final RedisTestRepository redisTestRepository;
    private final RedisCacheService redisCacheService;

    @Override
    public Response<?> getAll(Pageable pageable) {
        List<RedisTest> redisTests = redisTestRepository.findAll(pageable).getContent();
        for (RedisTest redisTest : redisTests) {
            redisCacheService.saveData(String.valueOf(redisTest.getId()), redisTest, 10, TimeUnit.MINUTES);
        }
        List<Object> redisCacheData = redisCacheService.getAllData();
        return Response.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .success(true)
                .message("Successfully fetched all data")
                .data(redisCacheData)
                .build();
    }
}
