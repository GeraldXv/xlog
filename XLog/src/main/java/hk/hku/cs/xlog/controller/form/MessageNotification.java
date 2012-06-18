package hk.hku.cs.xlog.controller.form;

/**
 * @author Gerald.xv at gmail.com
 * @version 创建时间：2012-6-18 下午2:51:47 
 */
public class MessageNotification {
	int allMessage;
	int gmailMessage;
	int twitterMessage;

	public int getAllMessage() {
		return allMessage;
	}

	public void setAllMessage(int allMessage) {
		this.allMessage = allMessage;
	}

	public int getGmailMessage() {
		return gmailMessage;
	}

	public void setGmailMessage(int gmailMessage) {
		this.gmailMessage = gmailMessage;
	}

	public int getTwitterMessage() {
		return twitterMessage;
	}

	public void setTwitterMessage(int twitterMessage) {
		this.twitterMessage = twitterMessage;
	}

}
