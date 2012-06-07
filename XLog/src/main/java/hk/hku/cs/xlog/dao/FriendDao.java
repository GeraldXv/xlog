package hk.hku.cs.xlog.dao;

import hk.hku.cs.xlog.entity.Friend;

import java.util.List;

public interface FriendDao {

	Friend get(Integer id);

	Integer save(Friend friend);

	void update(Friend friend);

	void saveOrUpdateAll(List<Friend> friends);

	void delete(Friend friend);

	void delete(Integer id);

	List<Friend> getFriends(String refName);

}
