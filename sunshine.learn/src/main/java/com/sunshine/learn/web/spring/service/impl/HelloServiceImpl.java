package com.sunshine.learn.web.spring.service.impl;

import com.sunshine.learn.web.spring.service.HelloService;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HelloServiceImpl implements HelloService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String welcome(String name) {
        helloRedis();
        helloMySql();
        return "Hello " + name;
    }

    private void helloMySql() {
        String sql = "select * from person";
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql);
        System.out.println(data);
    }

    private void helloRedis() {
        String k1 = redisTemplate.opsForValue().get("k1");
        redisTemplate.opsForValue().set("k2", "Hello Redis");
        String k11 = redisTemplate.opsForValue().get("k2");
        redisTemplate.opsForValue().set("k1", "Hello Redis");
        System.out.println(redisTemplate.keys("*"));
        log.info("k1: " + k1);
        log.info("k1: " + k11);
    }
}
