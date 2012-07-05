package hk.hku.cs.xlog.bo;

import hk.hku.cs.xlog.entity.Friend;
import hk.hku.cs.xlog.entity.Message;
import hk.hku.cs.xlog.entity.Status;
import hk.hku.cs.xlog.solr.PaginationSupport;
import hk.hku.cs.xlog.util.Pagination;

public interface SearchClient {

	PaginationSupport<Friend> searchFriend(String userName, String query, int page);

	PaginationSupport<Status> searchStatus(String userName, String query, int page, String range);

	PaginationSupport<Message> searchMessage(String userName, String query, int page, String range);

	Pagination<Status> searchTag(String userName, String query, int page);

	void createIndex();

	void updateIndex();

}
