package com.newidor.learn.redis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.newidor.learn.redis.dao.User;
import com.newidor.learn.redis.dao.UserDao;


/**
 * 使用spring-data-redis，类似于JDBC Template方式，进行简单对象的get、set
 */
public class SpringTest {
	private ApplicationContext app;
	private UserDao userDao;

	@Before
	public void before() throws Exception {
		app = new ClassPathXmlApplicationContext("applicationContext.xml");
		userDao = (UserDao) app.getBean("userDao");
	}

	@Test
	public void crud() {
		// -------------- Create ---------------
		String uid = "u123456";
		String address1 = "上海";
		User user = new User();
		user.setAddress(address1);
		user.setUid(uid);
		userDao.save(user);

		// ---------------Read ---------------
		user = userDao.read(uid);

		assertEquals(address1, user.getAddress());

		// --------------Update ------------
		String address2 = "北京";
		user.setAddress(address2);
		userDao.save(user);

		user = userDao.read(uid);

		assertEquals(address2, user.getAddress());

		// --------------Delete ------------
		userDao.delete(uid);
		user = userDao.read(uid);
		assertNull(user);
	}
}
