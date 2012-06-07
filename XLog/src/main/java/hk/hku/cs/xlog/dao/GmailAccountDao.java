package hk.hku.cs.xlog.dao;

import hk.hku.cs.xlog.entity.GmailAccount;

public interface GmailAccountDao {

	GmailAccount getByUserName(String refUser);

	void save(GmailAccount gmailAccount);

	void delete(GmailAccount gmailAccount);

	void delete(String refUser);

}
