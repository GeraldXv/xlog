package hk.hku.cs.xlog.adapter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.google.api.Google;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/applicationContext-social.xml", "classpath:applicationContext-dao.xml",
		"classpath:applicationContext-hibernate.xml", })
public class ProfileAdapterTest {
	private static final Logger logger = LoggerFactory.getLogger(ProfileAdapterTest.class);
	@Autowired
	FriendAdapter friendAdapter;
	@Autowired
	UsersConnectionRepository usersConnectionRepository;

	ConnectionRepository con;

	Twitter twitterApi;

	Google googleApi;
	@Rule
	public TestName name = new TestName();

	@Test
	public void test() {
		logger.debug("Running '{}'...", name.getMethodName());
		con = usersConnectionRepository.createConnectionRepository("GeraldXv");
		Connection<Twitter> twitter = con.findPrimaryConnection(Twitter.class);
		twitterApi = twitter.getApi();
		Connection<Google> google = con.findPrimaryConnection(Google.class);
		googleApi = google.getApi();
		System.out.println(twitterApi.friendOperations().getFriends().get(0).getName());
		// System.out.println(googleApi.personOperations().getPerson("110377639084744464746").get);
	}

}
