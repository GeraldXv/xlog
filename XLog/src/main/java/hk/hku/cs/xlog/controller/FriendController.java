package hk.hku.cs.xlog.controller;

import hk.hku.cs.xlog.bo.FriendClient;
import hk.hku.cs.xlog.bo.NotificationClient;
import hk.hku.cs.xlog.controller.form.SearchForm;
import hk.hku.cs.xlog.dao.UserDao;
import hk.hku.cs.xlog.entity.Friend;
import hk.hku.cs.xlog.solr.PaginationSupport;
import hk.hku.cs.xlog.util.Pagination;

import java.security.Principal;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gerald.xv at gmail.com
 * @version 创建时间：2012-6-18 下午2:49:45
 */
@Controller
@RequestMapping("/friend")
public class FriendController {

	private UserDao userDaoImpl;
	private FriendClient friendClient;
	private NotificationClient notificationClientImpl;

	@Inject
	public FriendController(UserDao userDaoImpl, FriendClient friendClient, NotificationClient notificationClientImpl) {
		this.userDaoImpl = userDaoImpl;
		this.friendClient = friendClient;
		this.notificationClientImpl = notificationClientImpl;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String searchGet(Principal currentUser, @RequestParam("query") String query) {
		return "redirect:/friend/facebook?page=1";
	}

	@RequestMapping(value = "/{providerId}", method = RequestMethod.GET)
	public String friendSearch(Principal currentUser, Model model, @PathVariable String providerId, @RequestParam("page") int page) {
		model.addAttribute("profileImage", userDaoImpl.getByUserName(currentUser.getName()).getProfileImage());
		Pagination<Friend> friends = friendClient.getFriends(currentUser.getName(), page, providerId);
		model.addAttribute("friends", friends.getItems());
		model.addAttribute("totalNum", friends.getTotalNum());
		model.addAttribute("totalPage", friends.getTotalPage());
		model.addAttribute("currentPage", friends.getCurrentPage());
		model.addAttribute("messageNotification", notificationClientImpl.getNotification(currentUser.getName()));
		return "friend";

	}
}
