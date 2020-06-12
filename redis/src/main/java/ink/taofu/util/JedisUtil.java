package ink.taofu.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public class JedisUtil {
    private static JedisPool jedisPool = null;
    private static String host;
    private static int port;
    private static int maxTotal;
    private static int maxIdle;
    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("redis");
        host = resourceBundle.getString("redis.host");
        port = Integer.parseInt(resourceBundle.getString("redis.port"));
        maxTotal = Integer.parseInt(resourceBundle.getString("redis.maxTotal"));
        maxIdle = Integer.parseInt(resourceBundle.getString("redis.maxIdle"));
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大连接数
        jedisPoolConfig.setMaxTotal(maxTotal);
        //活动连接数
        jedisPoolConfig.setMinIdle(maxIdle);
        jedisPool = new JedisPool(jedisPoolConfig, host, port);
    }
    public static Jedis getJedis () {
        return jedisPool.getResource();
    }
}
