package hk.hku.cs.xlog.dao.impl;

import hk.hku.cs.xlog.dao.GmailAccountDao;
import hk.hku.cs.xlog.entity.GmailAccount;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-dao.xml", "classpath:applicationContext-hibernate.xml" })
public class GmailAccountDaoImplTest {
	@Autowired
	GmailAccountDao GmailAccountDaoImpl;

	@Test
	public void test() {
		GmailAccount ga = new GmailAccount();
		ga.setRefUser("GeraldXv");
		ga.setAccount("gerald.xv@gmail.com");
		ga.setPassword("pkpbxjg0");
		GmailAccountDaoImpl.save(ga);
	}

}
