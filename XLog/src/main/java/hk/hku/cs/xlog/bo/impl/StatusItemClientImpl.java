package hk.hku.cs.xlog.bo.impl;

import hk.hku.cs.xlog.bo.StatusItemClient;
import hk.hku.cs.xlog.controller.form.TagContainner;
import hk.hku.cs.xlog.dao.impl.StatusDaoImpl;
import hk.hku.cs.xlog.dao.impl.TagDaoImpl;
import hk.hku.cs.xlog.entity.Status;
import hk.hku.cs.xlog.entity.Tag;

import java.util.HashSet;
import java.util.List;
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
		System.out.println(tagName);
		Status s = statusDaoImpl.get(statusId);
		System.out.println(statusId);
		System.out.println(s.getFromUser());
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
		System.out.println(tlist.size());
		s.getTags().addAll(tlist);
		statusDaoImpl.update(s);
	}

	@Override
	public TagContainner showTags(String idAtService, String fromUser) {
		List<Tag> tags = statusDaoImpl.getTagsbyIdAtservice(idAtService);
		List<Tag> tagFromUser = statusDaoImpl.getTagsbyFromUser(fromUser);
		List<Tag> tagsFromDB = tagDaoImpl.getTagByRank();
		tags.addAll(tagFromUser);
		tags.addAll(tagsFromDB);
		TagContainner tagc = new TagContainner();
		tagc.setTag1(tags.get(0).getTagName());
		tagc.setTag2(tags.get(1).getTagName());
		int i = 1;
		while (tagc.getTag1().equals(tagc.getTag2())) {
			i++;
			tagc.setTag2(tags.get(i).getTagName());
		}
		return tagc;
	}

}
