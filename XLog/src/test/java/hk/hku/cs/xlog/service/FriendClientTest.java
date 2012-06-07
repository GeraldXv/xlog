package hk.hku.cs.xlog.service;

import hk.hku.cs.xlog.adapter.FriendAdapter;
import hk.hku.cs.xlog.bo.FriendClient;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.google.api.Google;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/test/resources/applicationContext-social.xml",
		"classpath:applicationContext-dao.xml",
		"classpath:applicationContext-hibernate.xml", })
public class FriendClientTest {
	@Autowired
	FriendAdapter friendAdapter;
	@Autowired
	UsersConnectionRepository usersConnectionRepository;
	@Autowired
	FriendClient friendClient;

	ConnectionRepository con;

	Twitter twitterApi;

	Facebook facebookApi;

	Google googleApi;

	@Test
	public void test() {
		con = usersConnectionRepository.createConnectionRepository("GeraldXv");
		Connection<Twitter> twitter = con.findPrimaryConnection(Twitter.class);
		twitterApi = twitter.getApi();
		Connection<Facebook> facebook = con
				.findPrimaryConnection(Facebook.class);
		facebookApi = facebook.getApi();
		Connection<Google> google = con.findPrimaryConnection(Google.class);
		googleApi = google.getApi();
		// System.out.println(googleApi.personOperations().getPerson("110377639084744464746").get);
		List<FacebookProfile> ffriends = facebookApi.friendOperations()
				.getFriendProfiles(0, 500);
		List<TwitterProfile> tfriends = twitterApi.friendOperations()
				.getFriends();
		// List<Person> gfriends = googleApi.personOperations().;

		// friendClient.saveOrUpdateFacebookFriends("GeraldXv", ffriends);
		friendClient.saveOrUpdateTwitterFriends("GeraldXv", tfriends);
		// friendClient.saveFacebookFriends("GeraldXv", gfriends);
		// search.createFriendIndex(Pfriends);
		System.out.println(ffriends.size() + "----" + tfriends.size());
	}
}
