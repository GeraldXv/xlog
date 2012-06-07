package hk.hku.cs.xlog.dao.impl;

import hk.hku.cs.xlog.dao.FriendDao;
import hk.hku.cs.xlog.entity.Friend;

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
		return (List<Friend>) getHibernateTemplate().find(
				"from Friend where refName=?", refName);
	}

	@Override
	public void saveOrUpdateAll(List<Friend> friends) {
		// for (Friend friend : friends) {
		// // System.out.println(friend.getIdAtService());
		// getHibernateTemplate().saveOrUpdate(friend);
		// }
		getHibernateTemplate().saveOrUpdateAll(friends);
	}

}
