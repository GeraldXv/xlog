package hk.hku.cs.xlog.dao.impl;

import hk.hku.cs.xlog.dao.StatusDao;
import hk.hku.cs.xlog.entity.Status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-dao.xml", "classpath:applicationContext-hibernate.xml" })
public class StatusDaoImplTest {
	@Autowired
	StatusDao statusDaoImpl;

	@Test
	public void test() {

		// List<Status>s =statusDaoImpl.getStatusAllByTime("GeraldXv");
		// for(Status se:s){
		// System.out.println(se.toString());
		// }

		List<Status> s = statusDaoImpl.getStatusAllByTimeAndService("GeraldXv", "facebook");

		System.out.println(s.size());
	}

}
