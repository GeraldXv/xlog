package hk.hku.cs.xlog.bo;

import hk.hku.cs.xlog.controller.form.MessageNotification;

public interface TagClient {

	MessageNotification getNotification(String userName);
	
	void MakeAllMessageRead(String userName);
}
