package hk.hku.cs.xlog.dao.impl;

import hk.hku.cs.xlog.dao.UserConnectionDao;
import hk.hku.cs.xlog.entity.UserConnection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-dao.xml", "classpath:applicationContext-hibernate.xml" })
public class UserConnectionDaoImplTest {
	@Autowired
	UserConnectionDao userConnectionDaoImpl;

	@Test
	public void test() {

		UserConnection uc = userConnectionDaoImpl.getByNameAndProvider("GeraldXv", "twitter");

		System.out.println(uc.getProfileUrl());

	}

}
