package hk.hku.cs.xlog;

import hk.hku.cs.xlog.dao.UserDao;

import java.security.Principal;

import javax.inject.Inject;
import javax.inject.Provider;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	private final Provider<ConnectionRepository> connectionRepositoryProvider;

	private final UserDao userDaoImpl;

	@Inject
	public HomeController(Provider<ConnectionRepository> connectionRepositoryProvider, UserDao userDaoImpl) {
		this.connectionRepositoryProvider = connectionRepositoryProvider;
		this.userDaoImpl = userDaoImpl;
	}

	@RequestMapping("/home")
	public String home(Principal currentUser, Model model) {
		model.addAttribute("connectionsToProviders", getConnectionRepository().findAllConnections());
		model.addAttribute(userDaoImpl.getByUserName(currentUser.getName()));
		return "home";
	}

	private ConnectionRepository getConnectionRepository() {
		return connectionRepositoryProvider.get();
	}
}
