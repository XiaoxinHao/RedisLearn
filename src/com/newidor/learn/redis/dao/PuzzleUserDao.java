package com.newidor.learn.redis.dao;



public interface PuzzleUserDao {
	/**
	 * @param uid
	 * @param address
	 */
	void save(PuzzleUser puzzleUser);

	/**
	 * @param uid
	 * @return
	 */
	PuzzleUser read(String uid);

	/**
	 * @param uid
	 */
	void delete(String uid);
}
