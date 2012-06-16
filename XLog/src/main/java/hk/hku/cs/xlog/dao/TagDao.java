package hk.hku.cs.xlog.dao;

import hk.hku.cs.xlog.entity.Tag;

import java.util.List;

public interface TagDao {
	Tag get(String tagName);

	void save(Tag tag);

	void update(Tag tag);

	void delete(Tag tag);

	void saveOrUpdate(Tag tag);

	List<Tag> getMessagesByRank();

}
