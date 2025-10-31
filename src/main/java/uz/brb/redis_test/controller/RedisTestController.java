package uz.brb.redis_test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uz.brb.redis_test.dto.response.Response;
import uz.brb.redis_test.entity.RedisTest;
import uz.brb.redis_test.repository.RedisTestRepository;
import uz.brb.redis_test.service.RedisTestService;
import uz.brb.redis_test.service.logic.RedisCacheService;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/redis")
@RequiredArgsConstructor
public class RedisTestController {
    private final RedisTestRepository redisTestRepository;
    private final RedisCacheService redisCacheService;
    private final RedisTestService redisTestService;

    @PostMapping("/add")
    public Response<?> add(@RequestBody RedisTest redisTest) {
        redisTestRepository.save(redisTest);
        return Response.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .success(true)
                .message("Successfully added")
                .build();
    }

    @GetMapping("/get")
    public Response<?> get(@RequestParam Long id) {
        String key = String.valueOf(id);
        Object data = redisCacheService.getData(key);
        if (data != null) {
            return Response.builder()
                    .code(HttpStatus.OK.value())
                    .status(HttpStatus.OK)
                    .success(true)
                    .message("Successfully found")
                    .data(data)
                    .build();
        } else {
            RedisTest redisTest = redisTestRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("RedisTest not found"));
            redisCacheService.saveData(key, redisTest, 10, TimeUnit.MINUTES);
            return Response.builder()
                    .code(HttpStatus.OK.value())
                    .status(HttpStatus.OK)
                    .success(true)
                    .message("Successfully added")
                    .data(redisTest)
                    .build();
        }
    }

    @GetMapping("/getAll")
    public Response<?> getAll(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                              @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return redisTestService.getAll(PageRequest.of(page, size));
    }
}
