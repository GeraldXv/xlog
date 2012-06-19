package hk.hku.cs.xlog.controller;

import hk.hku.cs.xlog.controller.form.SignupForm;
import hk.hku.cs.xlog.dao.UserDao;
import hk.hku.cs.xlog.entity.User;
import hk.hku.cs.xlog.exception.UsernameAlreadyInUseException;
import hk.hku.cs.xlog.util.SignInUtils;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

@Controller
public class SignupController {

	@Inject
	private UserDao userDaoImpl;;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Valid SignupForm form, BindingResult formBinding, WebRequest request) {
		if (formBinding.hasErrors()) {
			return null;
		}
		User user = createAccount(form, formBinding);
		if (user != null) {
			SignInUtils.signin(user.getUserName());
			return "redirect:/source/";
		}
		return null;
	}

	private User createAccount(SignupForm form, BindingResult formBinding) {
		try {
			User user = new User(form.getUsername(), form.getPassword(), form.getEmail());
			userDaoImpl.save(user);
			return user;
		} catch (UsernameAlreadyInUseException e) {
			formBinding.rejectValue("username", "user.duplicateUsername", "already in use");
			return null;
		}
	}

}
