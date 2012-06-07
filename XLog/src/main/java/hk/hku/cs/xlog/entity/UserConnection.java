package hk.hku.cs.xlog.entity;

import java.io.Serializable;

public class UserConnection implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1602093090843920297L;
	private UserConPK userConPK;
	private String displayName;
	private String profileUrl;
	private String imageUrl;

	public UserConPK getUserConPK() {
		return userConPK;
	}

	public void setUserConPK(UserConPK userConPK) {
		this.userConPK = userConPK;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
