package hk.hku.cs.xlog.controller;

import hk.hku.cs.xlog.controller.form.SignupForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SigninController {

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public void signin(Model model) {
		model.addAttribute("signupForm", new SignupForm());
	}
}