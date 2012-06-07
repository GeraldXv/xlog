package hk.hku.cs.xlog.entity;

import java.io.Serializable;

public class UserConPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1184037616838034343L;
	private String userId;
	private String providerId;
	private String providerUserId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getProviderUserId() {
		return providerUserId;
	}

	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}

}
