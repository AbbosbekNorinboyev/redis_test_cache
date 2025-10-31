package uz.brb.redis_test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import uz.brb.redis_test.dto.response.Response;
import uz.brb.redis_test.entity.RedisTest;
import uz.brb.redis_test.service.RedisTestService;

@RestController
@RequestMapping("/api/redis")
@RequiredArgsConstructor
public class RedisTestController {
    private final RedisTestService redisTestService;

    @PostMapping("/add")
    public Response<?> add(@RequestBody RedisTest redisTest) {
        return redisTestService.add(redisTest);
    }

    @GetMapping("/get")
    public Response<?> get(@RequestParam Long id) {
        return redisTestService.get(id);
    }

    @GetMapping("/getAll")
    public Response<?> getAll(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                              @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return redisTestService.getAll(PageRequest.of(page, size));
    }
}
