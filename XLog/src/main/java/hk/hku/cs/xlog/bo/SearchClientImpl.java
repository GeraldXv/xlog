package hk.hku.cs.xlog.bo;

import hk.hku.cs.xlog.search.SearchServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.TwitterProfile;

public class SearchClientImpl implements SearchClient {
	@Autowired
	private SearchServiceImpl searchServiceImpl;

	@Override
	public void searchFriends() {
		// TODO Auto-generated method stub

	}

	@Override
	public void searchMessage() {
		// TODO Auto-generated method stub

	}

	@Override
	public void searchStatus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void searchTag() {
		// TODO Auto-generated method stub

	}

	@Override
	public void createTwitterFriendsIndex(String userName, List<TwitterProfile> tProfileList) {

		searchServiceImpl.createFriendIndex();

	}

	@Override
	public void updateFriendsIndex() {
		// TODO Auto-generated method stub

	}

}
