package hk.hku.cs.xlog.search;

import hk.hku.cs.xlog.entity.Friend;
import hk.hku.cs.xlog.entity.Message;
import hk.hku.cs.xlog.entity.Status;
import hk.hku.cs.xlog.solr.PaginationSupport;

import java.util.List;

public interface SearchService {

	void createIndex(String serverName);

	void updateIndex(String serverName);

	void createFriendIndex();

	void updateFriendIndex();

	void createStatusIndex();

	void updateStatusIndex();

	void createMessageIndex();

	void updateMessageIndex();

	List<Friend> searchFriends(String query);

	PaginationSupport<Friend> searchFriends(String userName,String query, int start, int rows);

	List<Status> searchStatus(String query);

	PaginationSupport<Status> searchStatus(String userName,String query, int start, int rows, String startTime, String endTime);

	List<Message> searchMessages(String query);

	PaginationSupport<Message> searchMessages(String userName,String query, int start, int rows, String startTime, String endTime);
}
