package hk.hku.cs.xlog.controller;

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

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String services(Principal currentUser, Model model) {
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
		if (providerId.equals("gmail"))
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
}
