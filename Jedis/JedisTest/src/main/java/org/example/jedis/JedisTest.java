package org.example.jedis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-31 16:47
 */

public class JedisTest {

    @Test
    public void testJedis() {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("name1", "luoyu1");
        System.out.println(jedis.get("name1"));
    }

    // 1. 这是 JUnit 测试方法注解，让这段代码可以直接运行
    @Test
    public void testJedisPool() {

        // 2. 创建**连接池配置对象**
        // 作用：告诉连接池：最多开多少连接、最少保持多少空闲...
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        // 3. 最大连接数：连接池里最多同时有 20 个连接
        // 超过 20 个请求来拿连接，就会等待
        jedisPoolConfig.setMaxTotal(20);

        // 4. 最大空闲连接数：最多允许 5 个连接闲着不用
        jedisPoolConfig.setMaxIdle(5);

        // 5. 最小空闲连接数：即使没人用，也保持 3 个连接待命
        // 目的：提升响应速度，不用频繁创建/销毁连接
        jedisPoolConfig.setMinIdle(3);

        // ---------------------------------------------------

        // 6. 创建**Jedis连接池**
        // 参数1：配置
        // 参数2：Redis 地址
        // 参数3：Redis 端口
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "localhost", 6379);

        // 7. 从连接池里**借一个连接**
        Jedis jedis = jedisPool.getResource();

        // ---------------------------------------------------

        // 8. 执行 Redis 命令：set key value
        jedis.set("name", "luoyu-pool");

        // 9. 执行 Redis 命令：get key，并且打印结果
        System.out.println(jedis.get("name"));

        // 10. 归还连接到连接池（不是关闭！是还给池子）
        jedis.close();

        // 11. 关闭整个连接池（测试结束用）
        jedisPool.close();
    }

    // 1. JUnit 测试注解 → 让这个方法可以直接运行
    @Test
    public void testJedisCluster() {

        // 2. 创建一个 Set 集合，用来存放 Redis 集群所有节点的地址
        // Redis 集群必须传入多个节点（不需要全部，一部分就行）
        Set<HostAndPort> set = new HashSet<>();

        // 3. 添加集群节点 1
        set.add(new HostAndPort("localhost", 6379));

        // 4. 添加集群节点 2
        set.add(new HostAndPort("localhost", 6380));

        // 5. 添加集群节点 3
        set.add(new HostAndPort("localhost", 6381));

        // ---------------------------------------------------

        // 6. 创建 JedisCluster 集群客户端
        // 它会自动发现整个集群所有节点、自动分片、自动路由
        JedisCluster jedisCluster = new JedisCluster(set);

        // 7. 向集群中 set 数据（key=name, value=luoyu）
        // 集群会**自动计算槽位 → 自动路由到对应节点存储**
        jedisCluster.set("name", "luoyu");

        // 8. 从集群中 get 数据
        // 集群自动找到数据所在的节点
        System.out.println(jedisCluster.get("name"));
    }
}
