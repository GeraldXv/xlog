package hk.hku.cs.xlog.service;

import hk.hku.cs.xlog.bo.StatusClient;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/applicationContext-social.xml", "classpath:applicationContext-dao.xml",
		"classpath:applicationContext-hibernate.xml", })
public class StatusClientTest {

	@Autowired
	UsersConnectionRepository usersConnectionRepository;
	@Autowired
	StatusClient statusClient;

	ConnectionRepository con;
	Twitter twitterApi;
	Facebook facebookApi;

	public boolean uniqueID() {

		return false;

	}

	@Test
	public void test() {
		con = usersConnectionRepository.createConnectionRepository("GeraldXv");
		Connection<Twitter> twitter = con.findPrimaryConnection(Twitter.class);
		Connection<Facebook> facebook = con.findPrimaryConnection(Facebook.class);
		twitterApi = twitter.getApi();
		facebookApi = facebook.getApi();
		List<Post> posts = facebookApi.feedOperations().getHomeFeed(0, 50);
		System.out.println(posts.get(0).getCreatedTime());
		// List<Tweet> tweets =
		// twitterApi.timelineOperations().getHomeTimeline(0, 200);
		//
		// statusClient.saveOrUpdateFacebookStatus("GeraldXv", posts);
		// statusClient.saveOrUpdateTwitterStatus("GeraldXv", tweets);

	}
}
