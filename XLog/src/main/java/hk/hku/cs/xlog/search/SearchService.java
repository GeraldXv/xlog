package hk.hku.cs.xlog.search;

import hk.hku.cs.xlog.entity.Friend;
import hk.hku.cs.xlog.entity.Message;
import hk.hku.cs.xlog.entity.Status;

import java.util.List;

;

public interface SearchService {

	void createIndex(String serverName);

	void updateIndex(String serverName);

	// PaginationSupport<Class> searchIndex(String query,Class<T> cls,String
	// serverName);

	void createFriendIndex();

	void updateFriendIndex();

	void createStatusIndex();

	void updateStatusIndex();

	void createMessageIndex();

	void updateMessageIndex();

	List<Friend> searchFriends(String query);

	List<Status> searchStatus(String query);

	List<Message> searchMessages(String query);

}
