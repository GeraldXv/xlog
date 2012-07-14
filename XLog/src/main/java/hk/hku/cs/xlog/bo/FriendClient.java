package hk.hku.cs.xlog.bo;

import hk.hku.cs.xlog.adapter.FriendAdapter;
import hk.hku.cs.xlog.dao.impl.FriendDaoImpl;
import hk.hku.cs.xlog.entity.Friend;
import hk.hku.cs.xlog.util.Pagination;

import java.util.List;

import javax.inject.Inject;

import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.google.api.plus.person.Person;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;

@Service
public class FriendClient {
	@Inject
	private FriendAdapter friendAdapter;
	@Inject
	private FriendDaoImpl friendDaoImpl;

	public void saveOrUpdateTwitterFriends(String userName, List<TwitterProfile> tFriends) {
		friendDaoImpl.saveOrUpdateAll(friendAdapter.twitterProfileListAdapter(userName, tFriends));

	}

	public void saveOrUpdateFacebookFriends(String userName, List<FacebookProfile> fFriends) {
		friendDaoImpl.saveOrUpdateAll(friendAdapter.facebookProfileListAdapter(userName, fFriends));

	}

	public void saveOrUpdateGplusFriends(String userName, List<Person> gFriends) {
		friendDaoImpl.saveOrUpdateAll(friendAdapter.googleProfileListAdapter(userName, gFriends));

	}

	public Pagination<Friend> getFriends(String userName, int page, String providerId) {
		return friendDaoImpl.getFriends(userName, providerId, page);

	}
}
