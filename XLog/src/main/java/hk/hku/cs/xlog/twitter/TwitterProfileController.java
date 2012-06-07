package hk.hku.cs.xlog.twitter;

import hk.hku.cs.xlog.adapter.FriendAdapter;

import java.security.Principal;

import javax.inject.Inject;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TwitterProfileController {

	@Inject
	private ConnectionRepository connectionRepository;

	@Inject
	private FriendAdapter profileadapter;

	@RequestMapping(value = "/twitter", method = RequestMethod.GET)
	public String home(Principal currentUser, Model model) {
		Connection<Twitter> connection = connectionRepository
				.findPrimaryConnection(Twitter.class);
		if (connection == null) {
			return "redirect:/connect/twitter";
		}
		model.addAttribute("profile", connection.getApi().userOperations()
				.getUserProfile());
		System.out.println(profileadapter.twitterProfileAdapter(
				connection.getApi().userOperations().getUserProfile())
				.toString());
		return "twitter/profile";
	}

}