package hk.hku.cs.xlog.bo;

import hk.hku.cs.xlog.adapter.FriendAdapter;
import hk.hku.cs.xlog.dao.impl.FriendDaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.google.api.plus.person.Person;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;

@Service
public class FriendClient {
	@Autowired
	private FriendAdapter friendAdapter;
	@Autowired
	private FriendDaoImpl friendDaoImpl;

	public void saveOrUpdateTwitterFriends(String userName,
			List<TwitterProfile> tFriends) {
		friendDaoImpl.saveOrUpdateAll(friendAdapter.twitterProfileListAdapter(
				userName, tFriends));

	}

	public void saveOrUpdateFacebookFriends(String userName,
			List<FacebookProfile> fFriends) {
		friendDaoImpl.saveOrUpdateAll(friendAdapter.facebookProfileListAdapter(
				userName, fFriends));

	}

	public void saveOrUpdateGplusFriends(String userName, List<Person> gFriends) {
		friendDaoImpl.saveOrUpdateAll(friendAdapter.googleProfileListAdapter(
				userName, gFriends));

	}
}
