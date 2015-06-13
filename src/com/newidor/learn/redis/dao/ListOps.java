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
	 * ѹջ
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long push(String key, String value) {
		return stringRedisTemplate.opsForList().leftPush(key, value);
	}

	/**
	 * ��ջ
	 * 
	 * @param key
	 * @return
	 */
	public String pop(String key) {
		return stringRedisTemplate.opsForList().leftPop(key);
	}

	/**
	 * ���
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long in(String key, String value) {
		return stringRedisTemplate.opsForList().rightPush(key, value);
	}

	/**
	 * ����
	 * 
	 * @param key
	 * @return
	 */
	public String out(String key) {
		return stringRedisTemplate.opsForList().leftPop(key);
	}

	/**
	 * ջ/���г�
	 * 
	 * @param key
	 * @return
	 */
	public Long length(String key) {
		return stringRedisTemplate.opsForList().size(key);
	}

	/**
	 * ��Χ����
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
	 * �Ƴ�
	 * 
	 * @param key
	 * @param i
	 * @param value
	 */
	public void remove(String key, long i, String value) {
		stringRedisTemplate.opsForList().remove(key, i, value);
	}

	/**
	 * ����
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	public String index(String key, long index) {
		return stringRedisTemplate.opsForList().index(key, index);
	}

	/**
	 * ��ֵ
	 * 
	 * @param key
	 * @param index
	 * @param value
	 */
	public void set(String key, long index, String value) {
		stringRedisTemplate.opsForList().set(key, index, value);
	}

	/**
	 * �ü�
	 * 
	 * @param key
	 * @param start
	 * @param end
	 */
	public void trim(String key, long start, int end) {
		stringRedisTemplate.opsForList().trim(key, start, end);
	}
}
