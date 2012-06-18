package hk.hku.cs.xlog.bo.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import hk.hku.cs.xlog.bo.NotificationClient;
import hk.hku.cs.xlog.controller.form.MessageNotification;
import hk.hku.cs.xlog.dao.MessageDao;

/**
 * @author Gerald.xv at gmail.com
 */
@Service
public class NotificationClientImpl implements NotificationClient {
	@Inject
	MessageDao messageDaoImpl;

	@Override
	public MessageNotification getNotification(String userName) {
		MessageNotification mn = new MessageNotification();
		int gmailMessage = messageDaoImpl.getUnreadGmail(userName);
		int twitterMessage = messageDaoImpl.getUnreadTwitterMessage(userName);
		mn.setGmailMessage(gmailMessage);
		mn.setTwitterMessage(twitterMessage);
		mn.setAllMessage(gmailMessage + twitterMessage);
		return mn;
	}

	@Override
	public void MakeAllMessageRead(String userName) {
		messageDaoImpl.markAllRead(userName);

	}

}
