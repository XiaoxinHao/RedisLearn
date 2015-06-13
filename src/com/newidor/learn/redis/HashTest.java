package com.newidor.learn.redis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.newidor.learn.redis.dao.PuzzleUser;
import com.newidor.learn.redis.dao.PuzzleUserDao;

/**
 * 使用spring-data-redis，测试Redis对hashmap支持
 * @author Administrator
 *
 */
public class HashTest {
	
	private ApplicationContext app;
	private PuzzleUserDao puzzleUserDao;

	@Before
	public void before() throws Exception {
		app = new ClassPathXmlApplicationContext("applicationContext.xml");
		puzzleUserDao = (PuzzleUserDao) app.getBean("puzzleUserDao");
	}

	@Test
	public void crud() {
		// -------------- Create ---------------
		String uid = "u123456";
		String address1 = "上海";
		PuzzleUser puzzleUser = new PuzzleUser();
		puzzleUser.setAddress(address1);
		puzzleUser.setUid(uid);
		puzzleUser.setMobile("13800138000");
		puzzleUser.setPostCode("100859");
		
		puzzleUserDao.save(puzzleUser);

		// ---------------Read ---------------
		puzzleUser = puzzleUserDao.read(uid);

		assertEquals(address1, puzzleUser.getAddress());

		// --------------Update ------------
		String address2 = "北京";
		puzzleUser.setAddress(address2);
		puzzleUserDao.save(puzzleUser);

		puzzleUser = puzzleUserDao.read(uid);

		assertEquals(address2, puzzleUser.getAddress());

		// --------------Delete ------------
		puzzleUserDao.delete(uid);
		puzzleUser = puzzleUserDao.read(uid);
		assertNull(puzzleUser);
	}

}
