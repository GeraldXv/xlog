package hk.hku.cs.xlog.bo;

import hk.hku.cs.xlog.dao.GmailAccountDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckSourceClient {
	@Autowired
	private GmailAccountDao gmailAccountDaoImpl;

	public boolean isGmailConnected(String userName) {
		return (gmailAccountDaoImpl.getByUserName(userName) != null);
	}

}
