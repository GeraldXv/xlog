package hk.hku.cs.xlog.dao;

import hk.hku.cs.xlog.entity.Message;

import java.util.List;

public interface MessageDao {

	Message get(String id);

	void save(Message message);

	void update(Message message);

	void saveOrUpdateAll(List<Message> messages);

	void delete(Message message);

	void delete(String id);

	int getUnreadGmail(String refUser);

	int getUnreadTwitterMessage(String refUser);

	void markAllRead(String refUser);

	List<Message> getMessages(String refName);

	List<Message> getMessagesByUserName(String refUser, String fromUser);

	List<Message> getMessagesByTime(String refName, String gmailaccount, String twitteraccount);

}
