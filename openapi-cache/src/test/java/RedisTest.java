import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @Author Lvxy
 * @Date 2021/2/26 15:40
 * @Version 1.0
 * @Desc
 */

@SpringBootTest(classes = RedisTest.class)
public class RedisTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void test1(){
        redisTemplate.opsForValue().set("name","吕星宇",10, TimeUnit.SECONDS);
    }
}
