package com.newidor.learn.redis.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 
 * @author snowolf
 * @version 1.0
 * @since 1.0
 */
@Component("listOps")
public class ListOps {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	public StringRedisTemplate getStringRedisTemplate() {
		return stringRedisTemplate;
	}
	public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
		this.stringRedisTemplate = stringRedisTemplate;
	}

	/**
	 * Ñ¹Õ»
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long push(String key, String value) {
		return stringRedisTemplate.opsForList().leftPush(key, value);
	}

	/**
	 * ³öÕ»
	 * 
	 * @param key
	 * @return
	 */
	public String pop(String key) {
		return stringRedisTemplate.opsForList().leftPop(key);
	}

	/**
	 * Èë¶Ó
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long in(String key, String value) {
		return stringRedisTemplate.opsForList().rightPush(key, value);
	}

	/**
	 * ³ö¶Ó
	 * 
	 * @param key
	 * @return
	 */
	public String out(String key) {
		return stringRedisTemplate.opsForList().leftPop(key);
	}

	/**
	 * Õ»/¶ÓÁÐ³¤
	 * 
	 * @param key
	 * @return
	 */
	public Long length(String key) {
		return stringRedisTemplate.opsForList().size(key);
	}

	/**
	 * ·¶Î§¼ìË÷
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<String> range(String key, int start, int end) {
		return stringRedisTemplate.opsForList().range(key, start, end);
	}

	/**
	 * ÒÆ³ý
	 * 
	 * @param key
	 * @param i
	 * @param value
	 */
	public void remove(String key, long i, String value) {
		stringRedisTemplate.opsForList().remove(key, i, value);
	}

	/**
	 * ¼ìË÷
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	public String index(String key, long index) {
		return stringRedisTemplate.opsForList().index(key, index);
	}

	/**
	 * ÖÃÖµ
	 * 
	 * @param key
	 * @param index
	 * @param value
	 */
	public void set(String key, long index, String value) {
		stringRedisTemplate.opsForList().set(key, index, value);
	}

	/**
	 * ²Ã¼ô
	 * 
	 * @param key
	 * @param start
	 * @param end
	 */
	public void trim(String key, long start, int end) {
		stringRedisTemplate.opsForList().trim(key, start, end);
	}
}
