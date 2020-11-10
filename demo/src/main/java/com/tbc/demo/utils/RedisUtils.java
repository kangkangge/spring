package com.tbc.demo.utils;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.*;

import java.util.*;

@Slf4j
public class RedisUtils {

    public static void main(String[] args) {
        JedisCluster clusterJedis = getClusterJedis();
        clusterJedis.hset("cheshiyihao", "测试111", "111");
        clusterJedis.expire("cheshiyihao",9999);
        String hget = clusterJedis.hget("cheshiyihao", "测试111");
        System.out.println(hget);
    }


    /**
     * 实现redis keys 模糊查询
     *
     * @param pattern
     * @return
     * @author hq
     */
    private static List<String> redisKeys(String pattern) {
        log.info("开始模糊查询【{}】keys", pattern);
        List<String> keys = new ArrayList<>();
        //获取所有连接池节点
        Map<String, JedisPool> nodes = getClusterJedis().getClusterNodes();
        //遍历所有连接池，逐个进行模糊查询
        for (String k : nodes.keySet()) {
            log.debug("从【{}】获取keys", k);
            JedisPool pool = nodes.get(k);
            //获取Jedis对象，Jedis对象支持keys模糊查询
            Jedis connection = pool.getResource();
            try {
                keys.addAll(connection.keys(pattern));
            } catch (Exception e) {
                log.error("获取key异常", e);
            } finally {
                log.info("关闭连接");
                //一定要关闭连接！
                connection.close();
            }
        }
        log.info("已获取所有keys");
        return keys;
    }

    public static Jedis getJedis() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        return jedis;
    }

    public static JedisCluster getClusterJedis() {
        Set<HostAndPort> nodes = new HashSet<>();
        //通用
        nodes.add(new HostAndPort("172.21.0.19", 16383));
        nodes.add(new HostAndPort("172.21.0.19", 16384));
        nodes.add(new HostAndPort("172.21.0.19", 16382));
        return new JedisCluster(nodes);
    }
}
