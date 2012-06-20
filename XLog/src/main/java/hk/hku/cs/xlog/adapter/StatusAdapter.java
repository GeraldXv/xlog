package hk.hku.cs.xlog.adapter;

import hk.hku.cs.xlog.entity.Status;

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.facebook.api.Post;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Service;

@Service
public class StatusAdapter {

	private Status status;

	private List<Status> statusList;

	public Status facebookStatusAdapter(Post post) {
		if (post.getTo() != null)
			return null;
		if (post.getStory() != null && post.getStory().contains("commented"))
			return null;
		status = new Status();
		status.setIdAtService(post.getId());
		status.setServiceProvider("facebook");
		status.setUserImage("https://graph.facebook.com/" + post.getFrom().getId() + "/" + "picture" + "?type=normal");
		status.setFromUser(post.getFrom().getName());
		status.setCreatedTime(post.getCreatedTime());
		status.setPicture(post.getPicture());
		status.setContent(post.getMessage());
		status.setLink(post.getLink());
		if (post.getMessage() == null)
			status.setContent(post.getStory());
		if (post.getMessage() == null && post.getStory() == null)
			status.setContent(post.getDescription());
		if (post.getMessage() == null && post.getStory() == null && post.getDescription() == null)
			status.setContent(post.getName());

		return status;
	}

	public Status twitterStatusAdapter(Tweet tweet) {
		status = new Status();
		status.setIdAtService("" + tweet.getId());
		status.setServiceProvider("twitter");
		status.setFromUser(tweet.getFromUser());
		status.setCreatedTime(tweet.getCreatedAt());
		status.setContent(tweet.getText());
		status.setUserImage(tweet.getProfileImageUrl());

		return status;

	}

	public List<Status> facebookStatusListAdapter(String userName, List<Post> fPostList) {
		statusList = new ArrayList<Status>();
		for (Post fp : fPostList) {
			Status f = facebookStatusAdapter(fp);
			if (f != null) {
				f.setRefUser(userName);
				f.setStatusId(f.getIdAtService() + userName);
				statusList.add(f);
			}
		}

		return statusList;

	}

	public List<Status> twitterStatusListAdapter(String userName, List<Tweet> tTweetList) {
		statusList = new ArrayList<Status>();
		for (Tweet ft : tTweetList) {
			Status t = twitterStatusAdapter(ft);
			t.setRefUser(userName);
			t.setStatusId(t.getIdAtService() + userName);
			statusList.add(t);

		}

		return statusList;

	}

	// TODO GoogleAdapter

}
