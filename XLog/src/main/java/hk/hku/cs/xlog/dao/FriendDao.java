package hk.hku.cs.xlog.dao;

import hk.hku.cs.xlog.entity.Friend;
import hk.hku.cs.xlog.util.Pagination;

import java.util.List;

public interface FriendDao {

	Friend get(Integer id);

	Integer save(Friend friend);

	void update(Friend friend);

	void saveOrUpdateAll(List<Friend> friends);

	void delete(Friend friend);

	void delete(Integer id);

	List<Friend> getFriends(String refName);
	
	Pagination<Friend> getFriends(String userName,String providerId, int currentPage);

}
