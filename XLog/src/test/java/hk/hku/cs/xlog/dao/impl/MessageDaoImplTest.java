package hk.hku.cs.xlog.dao.impl;

import hk.hku.cs.xlog.adapter.MessageAdapter;
import hk.hku.cs.xlog.dao.MessageDao;
import hk.hku.cs.xlog.entity.Message;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-dao.xml",
		"classpath:applicationContext-hibernate.xml" })
public class MessageDaoImplTest {
	@Autowired
	MessageDao messageDaoImpl;
	MessageAdapter messageAdapter = new MessageAdapter();

	@Test
	public void test() {

		// GmailClientX Gc = new GmailClientX();
		// List<JavaMailGmailMessage> mlist = Gc.getMessage("Gerald.Xv",
		// "pkpbxjg0");
		//
		// List<Message> mXList = messageAdapter.gmailMessageListAdapter(
		// "GeraldXv", mlist);
		// messageDaoImpl.saveOrUpdateAll(mXList);
		List<Message> ml = messageDaoImpl.getMessagesByTime("GeraldXv",
				"gerald.xv@gmail.com", "Gerald.Xv");
		System.out.println(ml.size());
		for (Message m : ml) {
			System.out.println(m.toString());
		}
	}

}
