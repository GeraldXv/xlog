package hk.hku.cs.xlog.bo.impl;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;
import hk.hku.cs.xlog.bo.AccountClient;
import hk.hku.cs.xlog.dao.UserDao;
import hk.hku.cs.xlog.entity.User;

/**
 * @author Gerald.xv at gmail.com
 * @version 创建时间：2012-6-22 下午2:32:09
 */
@Service
public class AccountClientImpl implements AccountClient {
	@Inject
	Twitter twitter;
	@Inject
	Facebook facebook;
	@Inject
	private UserDao userDaoImpl;

	@Override
	public void saveProfile(String userName, String serviceProvider) {
		User u = userDaoImpl.getByUserName(userName);
		if (serviceProvider.equals("twitter")) {
			u.setProfileImage(twitter.userOperations().getUserProfile().getProfileImageUrl());
		} else if (serviceProvider.equals("facebook")) {
			u.setProfileImage("https://graph.facebook.com/" + facebook.userOperations().getUserProfile().getId() + "/" + "picture" + "?type=normal");
		}
		userDaoImpl.update(u);
	}

	@Override
	public List<String> getProfiles(String userName) {
		List<String> profileList = new ArrayList<String>();
		profileList.add(twitter.userOperations().getUserProfile().getProfileImageUrl());
		profileList.add("https://graph.facebook.com/" + facebook.userOperations().getUserProfile().getId() + "/" + "picture" + "?type=normal");
		return profileList;
	}

	@Override
	public String getProfile(String userName) {
		return userDaoImpl.getByUserName(userName).getProfileImage();
	}

}
