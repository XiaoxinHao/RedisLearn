package com.newidor.learn.redis;

import java.util.List;
import java.util.ResourceBundle;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * ʹ��Redis�����Դ���Jedis��������м�Key��Value�ԵĲ���
 * @author Administrator
 *
 */
public class JedisTest {
	
	
	/********************************************************** �򵥲��� *********************************************************************/
	
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
	
	
	/********************************************************** ʹ��Properties�����ļ� *********************************************************************/
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
		// �ӳ��л�ȡһ��Jedis����
		Jedis jedis = pool.getResource();
		String keys = "name";

		// ɾ����
		jedis.del(keys);
		// ������
		jedis.set(keys, "snowolf");
		// ȡ����
		String value = jedis.get(keys);

		System.out.println(value);

		// �ͷŶ����
		pool.returnResource(jedis);
	}
	
	

}
