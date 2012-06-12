package hk.hku.cs.xlog.controller;

import hk.hku.cs.xlog.bo.FriendClient;
import hk.hku.cs.xlog.bo.MessageClient;
import hk.hku.cs.xlog.bo.StatusClient;
import hk.hku.cs.xlog.dao.GmailAccountDao;
import hk.hku.cs.xlog.dao.MessageDao;
import hk.hku.cs.xlog.dao.StatusDao;
import hk.hku.cs.xlog.dao.TagDao;
import hk.hku.cs.xlog.dao.UserConnectionDao;
import hk.hku.cs.xlog.dao.UserDao;
import hk.hku.cs.xlog.entity.Message;
import java.security.Principal;
import java.util.List;
import javax.inject.Inject;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.google.api.Google;
import org.springframework.social.twitter.api.DirectMessage;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {
	@Inject
	private MessageDao messageDaoImpl;
	@Inject
	private StatusDao statusDaoImpl;
	@Inject
	private UserDao userDaoImpl;
	@Inject
	private TagDao tagDaoImpl;
	@Inject
	private GmailAccountDao gmailAccountDaoImpl;
	@Inject
	private UserConnectionDao userConnectionDaoImpl;
	@Inject
	UsersConnectionRepository usersConnectionRepository;
	@Inject
	FriendClient friendClient;
	@Inject
	StatusClient statusClient;
	@Inject
	MessageClient messageClient;
	ConnectionRepository con;
	Twitter twitterApi;
	Facebook facebookApi;
	Google googleApi;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String services(Principal currentUser, Model model) {
		sycInfromation(currentUser.getName());
		model.addAttribute("profileImage", userDaoImpl.getByUserName(currentUser.getName()).getProfileImage());
		model.addAttribute("statusList", statusDaoImpl.getStatusAllByTime(currentUser.getName()));
		System.out.println(statusDaoImpl.getStatusAllByTime(currentUser.getName()).size());
		model.addAttribute("tags", tagDaoImpl.getMessagesByRank());
		model.addAttribute("providerId", "all");

		return "index";
	}

	@RequestMapping(value = "/status/{providerId}", method = RequestMethod.GET)
	public String services(Principal currentUser, Model model, @PathVariable String providerId) {
		model.addAttribute("profileImage", userDaoImpl.getByUserName(currentUser.getName()).getProfileImage());
		if (!providerId.equals("gmail"))
			model.addAttribute("statusList", statusDaoImpl.getStatusAllByTimeAndService(currentUser.getName(), providerId));
		else {
			List<Message> mList = messageDaoImpl.getMessagesByTime(currentUser.getName(),
					gmailAccountDaoImpl.getByUserName(currentUser.getName()).getAccount(),
					userConnectionDaoImpl.getByNameAndProvider(currentUser.getName(), "twitter").getUserConPK().getProviderUserId());
			model.addAttribute("messageList", mList);
		}
		model.addAttribute("tags", tagDaoImpl.getMessagesByRank());
		return "index";
	}

	// internal helper
	public void sycInfromation(String userName) {
		con = usersConnectionRepository.createConnectionRepository(userName);
		Connection<Twitter> twitter = con.findPrimaryConnection(Twitter.class);
		twitterApi = twitter.getApi();
		Connection<Facebook> facebook = con.findPrimaryConnection(Facebook.class);
		facebookApi = facebook.getApi();
//		Connection<Google> google = con.findPrimaryConnection(Google.class);
//		googleApi = google.getApi();
		List<FacebookProfile> ffriends = facebookApi.friendOperations().getFriendProfiles(0, 500);
		List<TwitterProfile> tfriends = twitterApi.friendOperations().getFriends();
		List<DirectMessage> tRList = twitterApi.directMessageOperations().getDirectMessagesReceived();
		List<DirectMessage> tSList = twitterApi.directMessageOperations().getDirectMessagesSent();
		List<Post> posts = facebookApi.feedOperations().getHomeFeed(0, 50);
		List<Tweet> tweets = twitterApi.timelineOperations().getHomeTimeline(0, 200);
		friendClient.saveOrUpdateTwitterFriends(userName, tfriends);
		friendClient.saveOrUpdateFacebookFriends(userName, ffriends);
		messageClient.saveOrUpdateTwitterMessages(userName, tRList);
		messageClient.saveOrUpdateTwitterMessages(userName, tSList);
		statusClient.saveOrUpdateFacebookStatus(userName, posts);
		statusClient.saveOrUpdateTwitterStatus(userName, tweets);
	}
}
