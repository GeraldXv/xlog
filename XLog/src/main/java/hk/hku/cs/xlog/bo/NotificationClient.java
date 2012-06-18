package hk.hku.cs.xlog.bo;

import hk.hku.cs.xlog.controller.form.MessageNotification;

public interface NotificationClient {

	MessageNotification getNotification(String userName);
	
	void MakeAllMessageRead(String userName);
}
