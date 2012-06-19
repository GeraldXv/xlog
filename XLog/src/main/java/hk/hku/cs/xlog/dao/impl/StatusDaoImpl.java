package hk.hku.cs.xlog.dao.impl;

import hk.hku.cs.xlog.dao.StatusDao;
import hk.hku.cs.xlog.entity.Status;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class StatusDaoImpl extends HibernateDaoSupport implements StatusDao {

	@Override
	public Status get(String statusId) {
		return (Status) getHibernateTemplate().get(Status.class, statusId);
	}

	@Override
	public void save(Status status) {
		getHibernateTemplate().save(status);
	}

	@Override
	public void update(Status status) {
		getHibernateTemplate().update(status);

	}

	@Override
	public void saveOrUpdateAll(List<Status> status) {
		for (Status s : status)
			getHibernateTemplate().merge(s);

	}

	@Override
	public void delete(Status status) {
		getHibernateTemplate().delete(status);

	}

	@Override
	public void delete(String idAtService) {
		getHibernateTemplate().delete(get(idAtService));

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Status> getStatus(String refName) {
		return (List<Status>) getHibernateTemplate().find("from Status where refUser=?", refName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Status> getStatusAllByTime(String refName) {
		getHibernateTemplate().setMaxResults(7);
		return (List<Status>) getHibernateTemplate().find("from Status where refUser=? and deleted='0' order by createdTime desc", refName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Status> getStatusAllByTimeAndService(String refName, String service) {
		getHibernateTemplate().setMaxResults(10);
		return (List<Status>) getHibernateTemplate().find("from Status where refUser=? and serviceProvider=? and deleted='0' order by createdTime desc ",
				new Object[] { refName, service });
	}

}
