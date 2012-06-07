package hk.hku.cs.xlog.controller;

import hk.hku.cs.xlog.dao.UserDao;
import hk.hku.cs.xlog.entity.Friend;
import hk.hku.cs.xlog.entity.Message;
import hk.hku.cs.xlog.entity.Status;
import hk.hku.cs.xlog.search.SearchServiceImpl;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
public class SearchController {

	@Inject
	private UserDao userDaoImpl;
	@Autowired
	private SearchServiceImpl searchServiceImpl;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String searchGet(Principal currentUser, Model model,
			@RequestParam("query") String query) {
		model.addAttribute("profileImage",
				userDaoImpl.getByUserName(currentUser.getName())
						.getProfileImage());
		model.addAttribute("friends", searchServiceImpl.searchFriends(query));
		model.addAttribute("query", query);
		return "search";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(Principal currentUser, Model model,
			@RequestParam("query") String query,
			@RequestParam("type") String type)
			throws UnsupportedEncodingException {
		// String t1=query;
		// String query = new String(t1.getBytes("iso-8859-1"), "utf-8");
		if (type.equals("friend"))
			return "redirect:/search/friend?query=" + query;
		else if (type.equals("message"))
			return "redirect:/search/message?query=" + query;
		else
			return "redirect:/search/status?query=" + query;
	}

	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public String statusSearch(Principal currentUser, Model model,
			@RequestParam("query") String query) {
		model.addAttribute("profileImage",
				userDaoImpl.getByUserName(currentUser.getName())
						.getProfileImage());
		List<Status> statusList = searchServiceImpl.searchStatus(query);
		model.addAttribute("statusList", statusList);
		model.addAttribute("size", statusList.size());
		model.addAttribute("query", query);
		model.addAttribute("type", "status");
		return "searchStauts";

	}

	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public String messageSearch(Principal currentUser, Model model,
			@RequestParam("query") String query) {
		model.addAttribute("profileImage",
				userDaoImpl.getByUserName(currentUser.getName())
						.getProfileImage());
		List<Message> messageList = searchServiceImpl.searchMessages(query);
		model.addAttribute("messageList", messageList);
		model.addAttribute("size", messageList.size());
		model.addAttribute("query", query);
		model.addAttribute("type", "message");
		return "searchMessage";

	}

	@RequestMapping(value = "/friend", method = RequestMethod.GET)
	public String friendSearch(Principal currentUser, Model model,
			@RequestParam("query") String query) {
		model.addAttribute("profileImage",
				userDaoImpl.getByUserName(currentUser.getName())
						.getProfileImage());
		List<Friend> friends = searchServiceImpl.searchFriends(query);
		model.addAttribute("friends", friends);
		model.addAttribute("size", friends.size());
		model.addAttribute("query", query);
		model.addAttribute("type", "friend");
		return "searchFriend";

	}
}
