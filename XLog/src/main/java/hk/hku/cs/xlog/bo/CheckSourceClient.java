package hk.hku.cs.xlog.bo;

import hk.hku.cs.xlog.dao.GmailAccountDao;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class CheckSourceClient {
	@Inject
	private GmailAccountDao gmailAccountDaoImpl;

	public boolean isGmailConnected(String userName) {
		return (gmailAccountDaoImpl.getByUserName(userName) != null);
	}

}
