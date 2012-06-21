package hk.hku.cs.xlog.bo.impl;

import hk.hku.cs.xlog.bo.SearchClient;
import hk.hku.cs.xlog.entity.Friend;
import hk.hku.cs.xlog.entity.Message;
import hk.hku.cs.xlog.entity.Status;
import hk.hku.cs.xlog.search.SearchService;
import hk.hku.cs.xlog.solr.PaginationSupport;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class SearchClientImpl implements SearchClient {
	@Inject
	SearchService searchServiceImpl;
	private final int size = 7;
	private String startTime = null;
	private String endTime = "NOW/DAY";

	@Override
	public PaginationSupport<Friend> searchFriend(String userName, String query, int page) {

		return searchServiceImpl.searchFriends(userName, query, (page - 1) * size, size);
	}

	@Override
	public PaginationSupport<Status> searchStatus(String userName, String query, int page, String range) {
		if (range.equals("none")) {
			startTime = "none";
		} else if (range.equals("hour")) {
			startTime = "NOW-1HOUR";
		} else if (range.equals("day")) {
			startTime = "NOW-1DAY";
		} else if (range.equals("week")) {
			startTime = "NOW-7DAY";
		} else if (range.equals("month")) {
			startTime = "NOW-1MONTH";
		} else if (range.equals("year")) {
			startTime = "NOW-1YEAR";
		}
		return searchServiceImpl.searchStatus(userName, query, (page-1) * size, size, startTime, endTime);
	}

	@Override
	public PaginationSupport<Message> searchMessage(String userName, String query, int page, String range) {
		if (range.equals("none")) {
			startTime = "none";
		} else if (range.equals("hour")) {
			startTime = "NOW-1HOUR";
		} else if (range.equals("day")) {
			startTime = "NOW-1DAY";
		} else if (range.equals("week")) {
			startTime = "NOW-7DAY";
		} else if (range.equals("month")) {
			startTime = "NOW-1MONTH";
		} else if (range.equals("year")) {
			startTime = "NOW-1YEAR";
		}
		return searchServiceImpl.searchMessages(userName, query, (page-1) * size, size, startTime, endTime);
	}

	@Override
	public void createIndex() {
		searchServiceImpl.createFriendIndex();
		searchServiceImpl.createStatusIndex();
		searchServiceImpl.createMessageIndex();

	}

	@Override
	public void updateIndex() {
		searchServiceImpl.updateFriendIndex();
		searchServiceImpl.updateStatusIndex();
		searchServiceImpl.updateMessageIndex();

	}

}
