package hk.hku.cs.xlog.controller;

import hk.hku.cs.xlog.bo.CheckSourceClient;
import hk.hku.cs.xlog.bo.impl.NotificationClientImpl;
import hk.hku.cs.xlog.controller.form.GmailForm;
import hk.hku.cs.xlog.controller.form.SearchForm;
import hk.hku.cs.xlog.dao.UserDao;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/source")
public class SourceController {
	private Provider<ConnectionRepository> connectionRepositoryProvider;
	private final ConnectionFactoryLocator connectionFactoryLocator;
	private CheckSourceClient checkSourceClient;
	@Inject
	private UserDao userDaoImpl;
	@Inject
	NotificationClientImpl notificationClientImpl;

	@Inject
	public SourceController(Provider<ConnectionRepository> connectionRepositoryProvider, ConnectionFactoryLocator connectionFactoryLocator,
			CheckSourceClient checkSourceClient) {
		this.connectionRepositoryProvider = connectionRepositoryProvider;
		this.connectionFactoryLocator = connectionFactoryLocator;
		this.checkSourceClient = checkSourceClient;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String services(Principal currentUser, Model model) {
		Map<String, List<Connection<?>>> connections = getConnectionRepository().findAllConnections();
		model.addAttribute("providerIds", connectionFactoryLocator.registeredProviderIds());
		model.addAttribute("connectionMap", connections);
		model.addAttribute("isGmailconnected", checkSourceClient.isGmailConnected(currentUser.getName()));
		model.addAttribute("profileImage", userDaoImpl.getByUserName(currentUser.getName()).getProfileImage());
		model.addAttribute("messageNotification", notificationClientImpl.getNotification(currentUser.getName()));
		model.addAttribute("searchForm", new SearchForm());
		model.addAttribute("gmailForm", new GmailForm());
		return "source";
	}

	@RequestMapping(value = "/del/gmail", method = RequestMethod.POST)
	public String del(Principal currentUser, Model model) {
		checkSourceClient.delGmailAccount(currentUser.getName());
		return "redirect:/source/";
	}

	@RequestMapping(value = "/add/gmail", method = RequestMethod.POST)
	public String add(Principal currentUser, Model model, GmailForm gmailForm) {
		checkSourceClient.addGmailAccount(currentUser.getName(), gmailForm.getAccount(), gmailForm.getPassword());
		return "redirect:/source/";
	}

	private ConnectionRepository getConnectionRepository() {
		return connectionRepositoryProvider.get();
	}
}
