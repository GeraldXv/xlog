package hk.hku.cs.xlog.dao.impl;

import hk.hku.cs.xlog.dao.UserConnectionDao;
import hk.hku.cs.xlog.entity.UserConnection;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserConnectionDaoImpl extends HibernateDaoSupport implements
		UserConnectionDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<UserConnection> getByName(String refUser) {
		return (List<UserConnection>) getHibernateTemplate().find(
				"from UserConnection where userId=?  ", refUser);
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserConnection getByNameAndProvider(String refUser, String providerId) {
		List<UserConnection> ucList = (List<UserConnection>) getHibernateTemplate()
				.find("from UserConnection where userId=? and providerId=? ",
						new Object[] { refUser, providerId });
		if (ucList.size() != 0)
			return ucList.get(0);
		else
			return null;
	}
}
