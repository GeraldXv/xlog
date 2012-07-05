package hk.hku.cs.xlog.dao.impl;

import hk.hku.cs.xlog.dao.TagDao;
import hk.hku.cs.xlog.entity.Status;
import hk.hku.cs.xlog.entity.Tag;
import hk.hku.cs.xlog.util.Pagination;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class TagDaoImpl extends HibernateDaoSupport implements TagDao {
	@Override
	public Tag get(String tagName) {

		return (Tag) getHibernateTemplate().get(Tag.class, tagName);
	}

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
	public List<Tag> getTagByRank() {
		getHibernateTemplate().setMaxResults(8);
		return (List<Tag>) getHibernateTemplate().find("from Tag order by tagCount desc");
	}

	@Override
	public Pagination<Status> getStatusByTag(String tagName, int currentPage, String userName) {
		Tag tag = (Tag) getHibernateTemplate().get(Tag.class, tagName);
		List<Status> tem = new ArrayList<Status>();
		Set<Status> slist = tag.getStatus();
		if (slist.size() != 0)
			for (Status s : slist) {
				if (s.getRefUser().equals(userName))
					tem.add(s);
			}
		return new Pagination<Status>(tem, currentPage);
	}

}
