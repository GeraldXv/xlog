package hk.hku.cs.xlog.dao.impl;

import hk.hku.cs.xlog.dao.GmailAccountDao;
import hk.hku.cs.xlog.entity.GmailAccount;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class GmailAccountDaoImpl extends HibernateDaoSupport implements GmailAccountDao {

	@Override
	public GmailAccount getByUserName(String refUser) {
		return (GmailAccount) getHibernateTemplate().get(GmailAccount.class, refUser);
	}

	@Override
	public void save(GmailAccount gmailAccount) {
		getHibernateTemplate().save(gmailAccount);
	}

	@Override
	public void delete(GmailAccount gmailAccount) {
		getHibernateTemplate().delete(gmailAccount);

	}

	@Override
	public void delete(String refUser) {
		getHibernateTemplate().delete(getByUserName(refUser));

	}

}
