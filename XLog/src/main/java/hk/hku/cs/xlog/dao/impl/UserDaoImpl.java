package hk.hku.cs.xlog.dao.impl;

import hk.hku.cs.xlog.dao.UserDao;
import hk.hku.cs.xlog.entity.User;
import hk.hku.cs.xlog.exception.UsernameAlreadyInUseException;

import java.util.List;

import javax.inject.Inject;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
	@Inject
	private PasswordEncoder passwordEncoder;

	@Override
	public User get(Integer id) {
		return (User) getHibernateTemplate().get(User.class, id);
	}

	@Override
	public User getByUserName(String userName) {
		@SuppressWarnings("unchecked")
		List<User> users = getHibernateTemplate().find("from User where userName=?", userName);
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Integer save(User user) throws UsernameAlreadyInUseException {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return (Integer) getHibernateTemplate().save(user);
	}

	@Override
	public void update(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		getHibernateTemplate().update(user);
	}

	@Override
	public void delete(User user) {
		getHibernateTemplate().delete(user);
	}

	@Override
	public void delete(Integer id) {
		getHibernateTemplate().delete(get(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		return (List<User>) getHibernateTemplate().find("from User");
	}

}
