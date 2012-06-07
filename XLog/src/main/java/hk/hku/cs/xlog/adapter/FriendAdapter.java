package hk.hku.cs.xlog.adapter;

import hk.hku.cs.xlog.entity.Friend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.google.api.plus.person.Person;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;

@Service
public class FriendAdapter {

	private Friend friend;

	private List<Friend> profileList = new ArrayList<Friend>();

	public Friend facebookProfileAdapter(FacebookProfile facebookProfile) {
		friend = new Friend();
		friend.setIdAtService(facebookProfile.getId());
		friend.setServiceProvider("facebook");
		friend.setGender(facebookProfile.getGender());
		friend.setScreenName(facebookProfile.getFirstName() + " "
				+ facebookProfile.getLastName());
		friend.setEmail(facebookProfile.getEmail());
		friend.setImageUrl("https://graph.facebook.com/"
				+ facebookProfile.getId() + "/" + "picture" + "?type=normal");
		friend.setHomeLink(facebookProfile.getLink());
		return friend;
	}

	public Friend twitterProfileAdapter(TwitterProfile twitterProfile) {
		friend = new Friend();
		friend.setIdAtService("" + twitterProfile.getId());
		friend.setServiceProvider("twitter");
		friend.setName(twitterProfile.getScreenName());
		friend.setScreenName(twitterProfile.getName());
		friend.setHomeLink(twitterProfile.getProfileUrl());
		friend.setImageUrl(twitterProfile.getProfileImageUrl());
		return friend;

	}

	public Friend googleProfileAdapter(Person googleProfile) {
		friend = new Friend();
		friend.setIdAtService(googleProfile.getId());
		friend.setServiceProvider("google");
		friend.setGender(googleProfile.getGender());
		if (googleProfile.getDisplayName() != null)
			friend.setScreenName(googleProfile.getDisplayName());
		if (googleProfile.getEmails().get(0).getValue() != null)
			friend.setEmail(googleProfile.getEmails().get(0).getValue());
		friend.setImageUrl(googleProfile.getImageUrl());
		friend.setHomeLink(googleProfile.getUrls().get(0).getValue());
		return friend;
	}

	public List<Friend> twitterProfileListAdapter(String userName,
			List<TwitterProfile> tProfileList) {

		for (TwitterProfile friend : tProfileList) {
			Friend pr = twitterProfileAdapter(friend);
			pr.setRefUser(userName);
			profileList.add(pr);
		}

		return profileList;

	}

	public List<Friend> facebookProfileListAdapter(String userName,
			List<FacebookProfile> fProfileList) {

		for (FacebookProfile fr : fProfileList) {
			Friend f = facebookProfileAdapter(fr);
			f.setRefUser(userName);
			profileList.add(f);
		}

		return profileList;

	}

	public List<Friend> googleProfileListAdapter(String userName,
			List<Person> gProfileList) {

		for (Person friend : gProfileList) {
			Friend pr = googleProfileAdapter(friend);
			pr.setRefUser(userName);
			profileList.add(pr);
		}

		return profileList;

	}
}
