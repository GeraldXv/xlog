package hk.hku.cs.xlog.bo;

import java.util.List;

import org.springframework.social.twitter.api.TwitterProfile;

public interface SearchClient {

	void searchFriends();

	void searchMessage();

	void searchStatus();

	void searchTag();

	void updateFriendsIndex();

	void createTwitterFriendsIndex(String userName,
			List<TwitterProfile> tProfileList);

}
