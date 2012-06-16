package hk.hku.cs.xlog.bo;

import hk.hku.cs.xlog.adapter.StatusAdapter;
import hk.hku.cs.xlog.dao.impl.StatusDaoImpl;
import hk.hku.cs.xlog.entity.Status;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Service;

@Service
public class StatusClient {
	@Autowired
	private StatusAdapter statusAdapter;
	@Autowired
	private StatusDaoImpl statusDaoImpl;

	public void saveOrUpdateTwitterStatus(String userName, List<Tweet> tweets) {
		statusDaoImpl.saveOrUpdateAll(statusAdapter.twitterStatusListAdapter(userName, tweets));

	}

	public void saveOrUpdateFacebookStatus(String userName, List<Post> posts) {
		statusDaoImpl.saveOrUpdateAll(statusAdapter.facebookStatusListAdapter(userName, posts));
	}

	public List<Status> getTopStatus(String userName) {
		return null;
	}

	public void sendTwitterStatus(String text, String userName) {

	}

	public void sendFacebookStatus(String text, String userName) {

	}
}
