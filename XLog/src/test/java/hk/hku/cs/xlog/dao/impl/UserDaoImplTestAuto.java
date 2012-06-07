package hk.hku.cs.xlog.dao.impl;

import hk.hku.cs.xlog.dao.UserDao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-dao.xml",
		"classpath:applicationContext-hibernate.xml" })
public class UserDaoImplTestAuto {
	@Autowired
	UserDao userDaoImpl;

	@Test
	public void test() {
		System.out.println(userDaoImpl.get(1).toString());
	}

}
