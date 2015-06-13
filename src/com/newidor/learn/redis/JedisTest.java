package com.newidor.learn.redis;

import java.util.List;
import java.util.ResourceBundle;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * 使用Redis厂商自带的Jedis组件，进行简单Key、Value对的操作
 * @author Administrator
 *
 */
public class JedisTest {
	
	
	/********************************************************** 简单测试 *********************************************************************/
	
	@Test
	public void testSimpleAPI(){
		
		Jedis redis = new Jedis("192.168.1.109",6379);
		
		redis.set("name", "haoxiaoxin");
		redis.setex("congtent", 5, "hello");
		redis.mset("class","a","age","25");
		redis.append("content", "lucy");
		String content = redis.get("content");
		
		System.out.println(content);
		List<String> list = redis.mget("class","age");
		
		for (String string : list) {
			System.out.println(string);
		}
		
		redis.close();
		
	}
	
	
	/********************************************************** 使用Properties配置文件 *********************************************************************/
	private static JedisPool pool;
	static {
		ResourceBundle bundle = ResourceBundle.getBundle("redis");
		if (bundle == null) {
			throw new IllegalArgumentException(
					"[redis.properties] is not found!");
		}
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(Integer.valueOf(bundle
				.getString("redis.pool.maxIdle")));
		config.setTestOnBorrow(Boolean.valueOf(bundle
				.getString("redis.pool.testOnBorrow")));
		config.setTestOnReturn(Boolean.valueOf(bundle
				.getString("redis.pool.testOnReturn")));
		pool = new JedisPool(config, bundle.getString("redis.ip"),
				Integer.valueOf(bundle.getString("redis.port")));
	}
	
	@Test
	public void testBundle(){
		// 从池中获取一个Jedis对象
		Jedis jedis = pool.getResource();
		String keys = "name";

		// 删数据
		jedis.del(keys);
		// 存数据
		jedis.set(keys, "snowolf");
		// 取数据
		String value = jedis.get(keys);

		System.out.println(value);

		// 释放对象池
		pool.returnResource(jedis);
	}
	
	

}
