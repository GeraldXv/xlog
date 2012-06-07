package hk.hku.cs.xlog.service;

import hk.hku.cs.xlog.bo.MessageClient;
import hk.hku.cs.xlog.dao.GmailAccountDao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.twitter.api.DirectMessage;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/test/resources/applicationContext-social.xml",
		"classpath:applicationContext-dao.xml",
		"classpath:applicationContext-hibernate.xml", })
public class MessageClientTest {

	@Autowired
	UsersConnectionRepository usersConnectionRepository;
	@Autowired
	MessageClient messageClient;
	@Autowired
	GmailAccountDao gmailAccountDaoImpl;
	ConnectionRepository con;
	Twitter twitterApi;

	@Test
	public void test() {
		con = usersConnectionRepository.createConnectionRepository("GeraldXv");
		Connection<Twitter> twitter = con.findPrimaryConnection(Twitter.class);
		twitterApi = twitter.getApi();

		List<DirectMessage> tRList = twitterApi.directMessageOperations()
				.getDirectMessagesReceived();
		List<DirectMessage> tSList = twitterApi.directMessageOperations()
				.getDirectMessagesSent();

		messageClient.saveOrUpdateTwitterMessages("GeraldXv", tRList);
		messageClient.saveOrUpdateTwitterMessages("GeraldXv", tSList);
		// GmailClientX Gc = new GmailClientX();
		// GmailAccount gaccount =
		// gmailAccountDaoImpl.getByUserName("GeraldXv");
		// List<JavaMailGmailMessage> mlist =
		// Gc.getMessage(gaccount.getAccount(),
		// gaccount.getPassword());
		//
		// messageClient.saveOrUpdateGmailMessages("GeraldXv", mlist);
	}
}
