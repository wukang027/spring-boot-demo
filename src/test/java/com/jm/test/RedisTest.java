package com.jm.test;

import com.jm.biz.redis.RedisClient;
import com.jm.domain.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by wukan on 2017-12-24-0024.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RedisClient redisClient;

    @Test
    public void set() throws Exception {
        Player player = new Player();
        player.setName("莱昂纳德");
        player.setId(5);
        player.setPoints(27.1);
        redisClient.set(player.getId().toString(),player);
    }

    @Test
    public void get() throws Exception {
        String string = redisClient.get("5");
        System.out.println("get.str="+string);
        System.out.println(redisClient.getObject("5",Player.class));
    }

    @Test
    public void del() throws Exception {
        Long string = redisClient.del("5");
        System.out.println("get.str="+string);
    }
}
