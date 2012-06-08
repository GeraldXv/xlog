package hk.hku.cs.xlog.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Status {
	private String idAtService;

	private String serviceProvider;

	private String refUser;

	private String fromUser;

	private Date createdTime;

	private String content;

	private String picture;

	private String link;

	private String userImage;

	private boolean stared;

	private Set<Tag> tags = new HashSet<Tag>();

	public String getIdAtService() {
		return idAtService;
	}

	public void setIdAtService(String idAtService) {
		this.idAtService = idAtService;
	}

	public String getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public String getRefUser() {
		return refUser;
	}

	public void setRefUser(String refUser) {
		this.refUser = refUser;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public boolean isStared() {
		return stared;
	}

	public void setStared(boolean stared) {
		this.stared = stared;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Status [idAtService=" + idAtService + ", serviceProvider=" + serviceProvider + ", refUser=" + refUser + ", fromUser=" + fromUser
				+ ", createdTime=" + createdTime + ", content=" + content + ", picture=" + picture + ", link=" + link + ", userImage=" + userImage
				+ ", stared=" + stared + ", tags=" + tags + "]";
	}

	//
	// public static void main(String args[]){
	//
	// Status status=new Status();
	// status.setContent("hhhh");
	// status.setContent(null);
	// System.out.println(status.toString());
	// }
	//

	// // TODO not support at version 2
	// private String link;
	// // TODO not support at version 2
	// private List<Comment> comments;
}
