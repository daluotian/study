package ink.taofu.service;

import ink.taofu.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

import java.util.concurrent.TimeUnit;

public class DoService {
    //控制单元
    public void control (String id) {
        Jedis jedis = JedisUtil.getJedis();
        String value = jedis.get("compid" + id);
        try {
            if (value == null) {
                jedis.setex("compid" + id, 5, Long.MAX_VALUE - 10 + "");
            } else {
                Long times = jedis.incr("compid" + id);
                business(id, Long.MAX_VALUE - times);
            }
        } catch (JedisDataException e) {
            System.err.println("使用达到次数上限请申请会员");
        } finally {
            jedis.close();
        }
    }
    //业务操作
    public void business (String id, Long times) {
        System.out.println(id + "用户开始执行操作第" + (10 - times) + "次");
    }
}

class MyThread extends Thread {
    
    DoService doService = new DoService();
    
    @Override
    public void run() {
        while (true) {
            doService.control("初级用户");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class main {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}