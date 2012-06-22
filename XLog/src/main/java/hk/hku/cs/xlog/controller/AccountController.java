package hk.hku.cs.xlog.controller;

import hk.hku.cs.xlog.bo.AccountClient;
import hk.hku.cs.xlog.controller.form.SearchForm;

import java.security.Principal;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Inject
	AccountClient accountClientImpl;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getProfile(Principal currentUser, Model model) {
		model.addAttribute("profileImage",accountClientImpl.getProfile(currentUser.getName()));
		model.addAttribute("profiles",accountClientImpl.getProfiles(currentUser.getName()));
		model.addAttribute("searchForm", new SearchForm());
		return "account";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String setProfile(Principal currentUser, Model model) {
		
		model.addAttribute("profileImage",accountClientImpl.getProfile(currentUser.getName()));
		return "redirect:/account/";
	}
}
