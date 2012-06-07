package hk.hku.cs.xlog.bo;

public interface NotificationClient {
	void getNewStatusNum();

	void getNewFacebookStatusNum();

	void getNewTwitterStatusNum();

	void getGooglePlusStatusNum();

	void getNewGmailMessagesNum();

	void MinusNewStatusNum();

	void MinusNewFacebookStatusNum();

	void MinusNewTwitterStatusNum();

	void MinusGooglePlusStatusNum();

	void MinusNewGmailMessagesNum();

}
