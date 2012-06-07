package hk.hku.cs.xlog.dao;

import hk.hku.cs.xlog.entity.Message;

import java.util.List;

public interface MessageDao {

	Message get(String idAtService);

	void save(Message message);

	void update(Message message);

	void saveOrUpdateAll(List<Message> messages);

	void delete(Message message);

	void delete(String idAtService);

	List<Message> getMessages(String refName);

	List<Message> getMessagesByUserName(String refName, String fromUser);

	List<Message> getMessagesByTime(String refName, String gmailaccount,
			String twitteraccount);

}
