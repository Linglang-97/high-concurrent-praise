package cn.zheng.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Zheng Shaobo
 * @version 1.0   2021/3/26 20:11
 */
public class RedisTest {

    @Test
    public void testRedis() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        RedisTemplate redisTemplate = applicationContext.getBean("redisTemplate", RedisTemplate.class);
        redisTemplate.opsForValue().set("name", "ay");
        String name = (String)redisTemplate.opsForValue().get("name");
        System.out.println("Value of name is:" + name);
    }

}
