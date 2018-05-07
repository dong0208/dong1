package com.kaishengit;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class Jedis1 {
    @Test
    public void jedisHello(){
        Jedis jedis = new Jedis("192.168.0.144",6379);
        String pong = jedis.ping();
        System.out.println(pong);
        jedis.close();
    }
}
