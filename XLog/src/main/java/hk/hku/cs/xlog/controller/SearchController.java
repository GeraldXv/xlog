package hk.hku.cs.xlog.controller;

import hk.hku.cs.xlog.bo.SearchClient;
import hk.hku.cs.xlog.controller.form.SearchForm;
import hk.hku.cs.xlog.dao.UserDao;
import hk.hku.cs.xlog.entity.Friend;
import hk.hku.cs.xlog.entity.Message;
import hk.hku.cs.xlog.entity.Status;
import hk.hku.cs.xlog.solr.PaginationSupport;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Principal;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/search")
public class SearchController {

	@Inject
	private UserDao userDaoImpl;
	@Inject
	private SearchClient searchClientImpl;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String searchGet(Principal currentUser, Model model, @RequestParam("query") String query) {
		return "redirect:/search/status?query=" + query + "&page=0&range=none";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(Principal currentUser, @ModelAttribute("searchForm") SearchForm searchForm, @RequestParam("type") String type)
			throws UnsupportedEncodingException {
		String query = null;
		try {
			query = URLEncoder.encode(searchForm.getQuery(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (type.equals("friend"))
			return "redirect:/search/friend?query=" + query + "&page=1&range=none";
		else if (type.equals("message"))
			return "redirect:/search/message?query=" + query + "&page=1&range=none";
		else
			return "redirect:/search/status?query=" + query + "&page=1&range=none";
	}

	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public String statusSearch(Principal currentUser, Model model, @RequestParam("query") String query, @RequestParam("page") int page,
			@RequestParam("range") String range) {
		model.addAttribute("profileImage", userDaoImpl.getByUserName(currentUser.getName()).getProfileImage());
		PaginationSupport<Status> status = searchClientImpl.searchStatus(currentUser.getName(), query, page, range);
		model.addAttribute("statusList", status.getItems());
		model.addAttribute("totalNum", status.getTotalCount());
		model.addAttribute("totalPage", status.getTotalPage());
		model.addAttribute("currentPage", status.getCurrentPage());
		model.addAttribute("query", query);
		model.addAttribute("type", "status");
		model.addAttribute("searchForm", new SearchForm());
		return "searchStauts";

	}

	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public String messageSearch(Principal currentUser, Model model, @RequestParam("query") String query, @RequestParam("page") int page,
			@RequestParam("range") String range) {
		model.addAttribute("profileImage", userDaoImpl.getByUserName(currentUser.getName()).getProfileImage());
		PaginationSupport<Message> messages = searchClientImpl.searchMessage(currentUser.getName(), query, page, range);
		model.addAttribute("messageList", messages.getItems());
		model.addAttribute("totalNum", messages.getTotalCount());
		model.addAttribute("totalPage", messages.getTotalPage());
		model.addAttribute("currentPage", messages.getCurrentPage());
		model.addAttribute("query", query);
		model.addAttribute("type", "message");
		model.addAttribute("searchForm", new SearchForm());
		return "searchMessage";

	}

	@RequestMapping(value = "/friend", method = RequestMethod.GET)
	public String friendSearch(Principal currentUser, Model model, @RequestParam("query") String query, @RequestParam("page") int page) {
		model.addAttribute("profileImage", userDaoImpl.getByUserName(currentUser.getName()).getProfileImage());
		PaginationSupport<Friend> friends = searchClientImpl.searchFriend(currentUser.getName(), query, page);
		model.addAttribute("friends", friends.getItems());
		model.addAttribute("totalNum", friends.getTotalCount());
		model.addAttribute("totalPage", friends.getTotalPage());
		model.addAttribute("currentPage", friends.getCurrentPage());
		model.addAttribute("query", query);
		model.addAttribute("type", "friend");
		model.addAttribute("searchForm", new SearchForm());
		return "searchFriend";

	}

	@RequestMapping(value = "/createIndex")
	public @ResponseBody
	String create() {
		searchClientImpl.createIndex();
		return "BULID FINE";

	}

	@RequestMapping(value = "/updateIndex")
	public @ResponseBody
	String update() {
		searchClientImpl.updateIndex();
		return "UPDATE FINE";

	}
}
