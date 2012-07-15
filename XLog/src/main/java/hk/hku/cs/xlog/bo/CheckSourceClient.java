package hk.hku.cs.xlog.bo;

import hk.hku.cs.xlog.dao.GmailAccountDao;
import hk.hku.cs.xlog.entity.GmailAccount;

import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class CheckSourceClient {
	@Inject
	private GmailAccountDao gmailAccountDaoImpl;

	public boolean isGmailConnected(String userName) {
		return (gmailAccountDaoImpl.getByUserName(userName) != null);
	}

	public void delGmailAccount(String userName) {
		gmailAccountDaoImpl.delete(userName);
	}

	public void addGmailAccount(String userName, String account, String psw) {
		gmailAccountDaoImpl.save(new GmailAccount(userName, account, psw));
	}
}
