package hk.hku.cs.xlog.dao.impl;

import hk.hku.cs.xlog.dao.UserDao;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserDaoImplTest {
	static UserDao userDao;

	@BeforeClass
	public static void beforeClass() {
		String[] configLocations = new String[] {
				"classpath:applicationContext-hibernate.xml",
				"classpath:applicationContext-dao.xml" };
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				configLocations);
		userDao = (UserDao) ctx.getBean("userDaoImpl");
	}

	@Test
	public void test() {
		System.out.println(userDao.get(1).toString());
	}

}
