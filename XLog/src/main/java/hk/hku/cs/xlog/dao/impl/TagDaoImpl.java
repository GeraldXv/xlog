package hk.hku.cs.xlog.dao.impl;

import hk.hku.cs.xlog.dao.TagDao;
import hk.hku.cs.xlog.entity.Tag;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class TagDaoImpl extends HibernateDaoSupport implements TagDao {

	@Override
	public void save(Tag tag) {
		getHibernateTemplate().save(tag);
	}

	@Override
	public void delete(Tag tag) {
		getHibernateTemplate().delete(tag);

	}

	@Override
	public void update(Tag tag) {
		getHibernateTemplate().update(tag);

	}

	@Override
	public void saveOrUpdate(Tag tag) {
		getHibernateTemplate().saveOrUpdate(tag);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tag> getMessagesByRank() {
		getHibernateTemplate().setMaxResults(8);
		return (List<Tag>) getHibernateTemplate().find("from Tag order by tagCount desc");
	}

}
