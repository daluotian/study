package ink.taofu;

import redis.clients.jedis.Jedis;

public class JedisTest01 {
    
    public static void main(String[] args) {
        testJedis();
    }
    
    public static void testJedis () {
        //连接 reids
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //操作redis
        System.out.println(jedis.get("name"));
        //关闭连接
        jedis.close();
    }
}
