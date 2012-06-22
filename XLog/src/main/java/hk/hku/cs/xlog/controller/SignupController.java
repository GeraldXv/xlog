package hk.hku.cs.xlog.controller;

import hk.hku.cs.xlog.controller.form.SignupForm;
import hk.hku.cs.xlog.dao.UserDao;
import hk.hku.cs.xlog.entity.User;
import hk.hku.cs.xlog.exception.UsernameAlreadyInUseException;
import hk.hku.cs.xlog.util.SignInUtils;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SignupController {

	@Inject
	private UserDao userDaoImpl;;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute("signupForm") SignupForm form) {

		User user = createAccount(form);
		if (user != null) {
			SignInUtils.signin(user.getUserName());
			return "redirect:/source/";
		}
		return null;
	}

	private User createAccount(SignupForm form) {
		try {
			User user = new User(form.getUsername(), form.getPassword(), form.getEmail());
			userDaoImpl.save(user);
			return user;
		} catch (UsernameAlreadyInUseException e) {
			return null;
		}
	}

}
