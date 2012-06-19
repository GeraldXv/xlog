package hk.hku.cs.xlog.bo.impl;

import hk.hku.cs.xlog.bo.StatusItemClient;
import hk.hku.cs.xlog.dao.impl.StatusDaoImpl;
import hk.hku.cs.xlog.dao.impl.TagDaoImpl;
import hk.hku.cs.xlog.entity.Status;
import hk.hku.cs.xlog.entity.Tag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

/**
 * @author Gerald.xv at gmail.com
 * @version 创建时间：2012-6-15 下午2:08:43
 */
@Service
public class StatusItemClientImpl implements StatusItemClient {

	@Inject
	private StatusDaoImpl statusDaoImpl;
	@Inject
	private TagDaoImpl tagDaoImpl;

	@Override
	public void reply(String servicePovider, String statusId, String content) {

	}

	@Override
	public void markFav(String statusId) {
		Status s = statusDaoImpl.get(statusId);
		s.setStared(true);
		statusDaoImpl.update(s);
	}

	@Override
	public void removeFav(String statusId) {
		Status s = statusDaoImpl.get(statusId);
		s.setStared(false);
		statusDaoImpl.update(s);

	}

	@Override
	public void share(String servicePovider, String statusId, String content) {

	}

	@Override
	public void delete(String statusId) {
		Status s = statusDaoImpl.get(statusId);
		s.setDeleted(true);
		statusDaoImpl.update(s);

	}

	// TODO More than one Tag
	@Override
	public void addTags(String statusId, String tagName) {
		Status s = statusDaoImpl.get(statusId);
		Set<Tag> tlist = new HashSet<Tag>();
		Tag tag = null;
		if (tagDaoImpl.get(tagName) != null) {
			tag = tagDaoImpl.get(tagName);
			tag.setTagCount(tag.getTagCount() + 1);
			tagDaoImpl.update(tag);
		} else {
			tag = new Tag();
			tag.setTagName(tagName);
			tag.setTagCount(1);
			tagDaoImpl.save(tag);
		}
		tlist.add(tag);
		s.setTags(tlist);
		statusDaoImpl.update(s);
	}

	@Override
	public ArrayList<Tag> showTags(String idAtService) {
		return null;

	}

}
