package uz.brb.redis_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.brb.redis_test.entity.RedisTest;

@Repository
public interface RedisTestRepository extends JpaRepository<RedisTest, Long> {
}
