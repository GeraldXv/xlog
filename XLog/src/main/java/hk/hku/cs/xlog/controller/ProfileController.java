package hk.hku.cs.xlog.controller;

import hk.hku.cs.xlog.controller.form.SearchForm;
import hk.hku.cs.xlog.dao.UserDao;

import java.security.Principal;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/profile")
public class ProfileController {
	
	@Inject
	private UserDao userDaoImpl;
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String services(Principal currentUser, Model model) {
		model.addAttribute("profileImage", userDaoImpl.getByUserName(currentUser.getName()).getProfileImage());
		model.addAttribute("searchForm", new SearchForm());
		return "profile";
	}

	
}
