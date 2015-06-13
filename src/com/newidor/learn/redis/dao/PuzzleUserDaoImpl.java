package com.newidor.learn.redis.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

public class PuzzleUserDaoImpl implements PuzzleUserDao {

	private RedisTemplate<Serializable, Serializable> redisTemplate;
	public RedisTemplate<Serializable, Serializable> getRedisTemplate() {
		return redisTemplate;
	}
	public void setRedisTemplate(
			RedisTemplate<Serializable, Serializable> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void save(final PuzzleUser user) {
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize(
						"uc.user.info.uid." + user.getUid());
				BoundHashOperations<Serializable, byte[], byte[]> boundHashOperations = redisTemplate
						.boundHashOps(key);
				boundHashOperations.put(redisTemplate.getStringSerializer()
						.serialize("mobile"), redisTemplate
						.getStringSerializer().serialize(user.getMobile()));
				boundHashOperations.put(redisTemplate.getStringSerializer()
						.serialize("address"), redisTemplate
						.getStringSerializer().serialize(user.getAddress()));
				boundHashOperations.put(redisTemplate.getStringSerializer()
						.serialize("postCode"), redisTemplate
						.getStringSerializer().serialize(user.getPostCode()));
				connection.hMSet(key, boundHashOperations.entries());
				return null;
			}
		});
	}

	@Override
	public PuzzleUser read(final String uid) {
		return redisTemplate.execute(new RedisCallback<PuzzleUser>() {
			@Override
			public PuzzleUser doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize(
						"uc.user.info.uid." + uid);
				if (connection.exists(key)) {
					List<byte[]> value = connection.hMGet(
							key,
							redisTemplate.getStringSerializer().serialize(
									"address"),
							redisTemplate.getStringSerializer().serialize(
									"mobile"), 
							redisTemplate.getStringSerializer().serialize("postCode"));
					PuzzleUser puzzleUser = new PuzzleUser();
					String address = redisTemplate.getStringSerializer()
							.deserialize(value.get(0));
					puzzleUser.setAddress(address);
					String mobile = redisTemplate.getStringSerializer()
							.deserialize(value.get(1));
					puzzleUser.setMobile(mobile);
					String postCode = redisTemplate.getStringSerializer()
							.deserialize(value.get(2));
					puzzleUser.setPostCode(postCode);
					
					puzzleUser.setUid(uid);

					return puzzleUser;
				}
				return null;
			}
		});
	}


	@Override
	public void delete(final String uid) {
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) {
				connection.del(redisTemplate.getStringSerializer().serialize(
						"uc.user.info.uid." + uid));
				return null;
			}
		});
	}

}
