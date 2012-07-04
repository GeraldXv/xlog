package hk.hku.cs.xlog.controller;

import hk.hku.cs.xlog.bo.AccountClient;
import hk.hku.cs.xlog.bo.FriendClient;
import hk.hku.cs.xlog.bo.MessageClient;
import hk.hku.cs.xlog.bo.StatusClient;
import hk.hku.cs.xlog.bo.impl.NotificationClientImpl;
import hk.hku.cs.xlog.bo.impl.StatusItemClientImpl;
import hk.hku.cs.xlog.controller.form.SearchForm;
import hk.hku.cs.xlog.controller.form.StatusForm;
import hk.hku.cs.xlog.controller.form.TagContainner;
import hk.hku.cs.xlog.dao.GmailAccountDao;
import hk.hku.cs.xlog.dao.MessageDao;
import hk.hku.cs.xlog.dao.StatusDao;
import hk.hku.cs.xlog.dao.TagDao;
import hk.hku.cs.xlog.dao.UserDao;
import hk.hku.cs.xlog.entity.GmailAccount;
import hk.hku.cs.xlog.entity.Message;
import hk.hku.cs.xlog.gmail.GmailClientX;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.twitter.api.DirectMessage;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.gmail4j.javamail.JavaMailGmailMessage;

@Controller
@RequestMapping("/")
public class IndexController {
	private MessageDao messageDaoImpl;
	private StatusDao statusDaoImpl;
	private UserDao userDaoImpl;
	private TagDao tagDaoImpl;
	private GmailAccountDao gmailAccountDaoImpl;
	private FriendClient friendClient;
	private StatusClient statusClient;
	private MessageClient messageClient;
	private Twitter twitter;
	private Facebook facebook;
	private GmailClientX gmailClientX;
	private NotificationClientImpl notificationClientImpl;
	private StatusItemClientImpl statusItemClientImpl;
	private AccountClient accountClientImpl;

	private long updateGap = 3600000;

	@Inject
	public IndexController(MessageDao messageDaoImpl, StatusDao statusDaoImpl, UserDao userDaoImpl, TagDao tagDaoImpl, GmailAccountDao gmailAccountDaoImpl,
			FriendClient friendClient, StatusClient statusClient, MessageClient messageClient, Twitter twitter, Facebook facebook, GmailClientX gmailClientX,
			NotificationClientImpl notificationClientImpl, StatusItemClientImpl statusItemClientImpl, AccountClient accountClientImpl) {
		super();
		this.messageDaoImpl = messageDaoImpl;
		this.statusDaoImpl = statusDaoImpl;
		this.userDaoImpl = userDaoImpl;
		this.tagDaoImpl = tagDaoImpl;
		this.gmailAccountDaoImpl = gmailAccountDaoImpl;
		this.friendClient = friendClient;
		this.statusClient = statusClient;
		this.messageClient = messageClient;
		this.twitter = twitter;
		this.facebook = facebook;
		this.gmailClientX = gmailClientX;
		this.notificationClientImpl = notificationClientImpl;
		this.statusItemClientImpl = statusItemClientImpl;
		this.accountClientImpl = accountClientImpl;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String services(Principal currentUser, Model model) {
		Date date = new Date();
		if (userDaoImpl.getUpdateTime(currentUser.getName()) == 0)
			sycInfromation(currentUser.getName());
		else if (date.getTime() - userDaoImpl.getUpdateTime(currentUser.getName()) > updateGap)
			sycInfromation(currentUser.getName());
		model.addAttribute("profileImage", accountClientImpl.getProfile(currentUser.getName()));
		model.addAttribute("statusList", statusDaoImpl.getStatusAllByTime(currentUser.getName()));
		model.addAttribute("tags", tagDaoImpl.getTagByRank());
		model.addAttribute("messageNotification", notificationClientImpl.getNotification(currentUser.getName()));
		model.addAttribute("statusForm", new StatusForm());
		model.addAttribute("searchForm", new SearchForm());
		return "index";

	}

	@RequestMapping(value = "/status/{providerId}", method = RequestMethod.GET)
	public String services(Principal currentUser, Model model, @PathVariable String providerId) {
		model.addAttribute("profileImage", accountClientImpl.getProfile(currentUser.getName()));
		if (!providerId.equals("gmail"))
			model.addAttribute("statusList", statusDaoImpl.getStatusAllByTimeAndService(currentUser.getName(), providerId));
		else {
			List<Message> mList = messageDaoImpl.getMessagesByTime(currentUser.getName(),
					gmailAccountDaoImpl.getByUserName(currentUser.getName()).getAccount(), twitter.isAuthorized() ? twitter.userOperations().getUserProfile()
							.getId()
							+ "" : null);
			model.addAttribute("messageList", mList);
		}
		model.addAttribute("tags", tagDaoImpl.getTagByRank());
		model.addAttribute("statusForm", new StatusForm());
		model.addAttribute("searchForm", new SearchForm());
		model.addAttribute("messageNotification", notificationClientImpl.getNotification(currentUser.getName()));
		return "index";
	}

	@RequestMapping(value = "/status", method = RequestMethod.POST)
	public String send(Principal currentUser, Model model, @ModelAttribute("statusForm") StatusForm statusForm) {
		String[] f = statusForm.getProvider();
		if (f.length == 2) {
			facebook.feedOperations().updateStatus(statusForm.getText());
			twitter.timelineOperations().updateStatus(statusForm.getText());
		} else if (f.length != 0 && f[0].equals("facebook")) {
			facebook.feedOperations().updateStatus(statusForm.getText());
		} else if (f.length != 0 && f[0].equals("twitter")) {
			twitter.timelineOperations().updateStatus(statusForm.getText());
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/status/delete")
	public @ResponseBody
	String delete(@RequestParam("statusId") String statusId) {
		statusItemClientImpl.delete(statusId);
		return "true";
	}

	@RequestMapping(value = "/status/mark")
	public @ResponseBody
	String mark(@RequestParam("statusId") String statusId, @RequestParam("isMarked") String isMarked) {
		if (isMarked.equals("true")) {
			statusItemClientImpl.removeFav(statusId);
			return "true";
		} else {
			statusItemClientImpl.markFav(statusId);
			return "true";
		}

	}

	@RequestMapping(value = "/status/addTag")
	public @ResponseBody
	String addTag(@RequestParam("statusId") String statusId, @RequestParam("tag") String tag) {
		statusItemClientImpl.addTags(statusId, tag);
		return "true";
	}

	@RequestMapping(value = "/status/showTag")
	public @ResponseBody
	TagContainner showTag(@RequestParam("idAtService") String idAtService, @RequestParam("fromUser") String fromUser) {
		return statusItemClientImpl.showTags(idAtService, fromUser);
	}

	// internal helper
	public void sycInfromation(String userName) {
		if (twitter.isAuthorized()) {
			List<TwitterProfile> tfriends = twitter.friendOperations().getFriends();
			List<DirectMessage> tRList = twitter.directMessageOperations().getDirectMessagesReceived();
			List<DirectMessage> tSList = twitter.directMessageOperations().getDirectMessagesSent();
			List<Tweet> tweets = twitter.timelineOperations().getHomeTimeline(0, 200);
			messageClient.saveOrUpdateTwitterMessages(userName, tRList);
			messageClient.saveOrUpdateTwitterMessages(userName, tSList);
			friendClient.saveOrUpdateTwitterFriends(userName, tfriends);
			statusClient.saveOrUpdateTwitterStatus(userName, tweets);
		}

		if (facebook.isAuthorized()) {
			List<FacebookProfile> ffriends = facebook.friendOperations().getFriendProfiles(0, 500);
			List<Post> posts = facebook.feedOperations().getHomeFeed(0, 50);
			friendClient.saveOrUpdateFacebookFriends(userName, ffriends);
			statusClient.saveOrUpdateFacebookStatus(userName, posts);
		}

		// TODO google

		if (gmailAccountDaoImpl.getByUserName(userName) != null) {
			GmailAccount gaccount = gmailAccountDaoImpl.getByUserName(userName);
			List<JavaMailGmailMessage> mlist = gmailClientX.getUnreadMessage(gaccount.getAccount(), gaccount.getPassword());
			messageClient.saveOrUpdateGmailMessages(userName, mlist);
		}

		userDaoImpl.updateTime(userName);
	}
}
