package hk.hku.cs.xlog.dao.impl;

import hk.hku.cs.xlog.dao.FriendDao;
import hk.hku.cs.xlog.entity.Friend;
import hk.hku.cs.xlog.util.Pagination;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class FriendDaoImpl extends HibernateDaoSupport implements FriendDao {
	@Override
	public Friend get(Integer id) {
		return (Friend) getHibernateTemplate().get(Friend.class, id);
	}

	@Override
	public void delete(Integer id) {
		getHibernateTemplate().delete(get(id));
	}

	@Override
	public Integer save(Friend friend) {
		return (Integer) getHibernateTemplate().save(friend);
	}

	@Override
	public void update(Friend friend) {
		getHibernateTemplate().update(friend);

	}

	@Override
	public void delete(Friend friend) {
		getHibernateTemplate().delete(friend);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Friend> getFriends(String refName) {
		return (List<Friend>) getHibernateTemplate().find("from Friend where refUser=?", refName);
	}

	@Override
	public void saveOrUpdateAll(List<Friend> friends) {
		for (Friend f : friends)
			getHibernateTemplate().merge(f);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pagination<Friend> getFriends(String userName, String providerId, int currentPage) {
		return new Pagination<Friend>((List<Friend>) getHibernateTemplate().find("from Friend where refUser=? and serviceProvider=?",
				new Object[] { userName, providerId }), currentPage);
	}

}
