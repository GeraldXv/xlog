package hk.hku.cs.xlog.bo;

import hk.hku.cs.xlog.adapter.MessageAdapter;
import hk.hku.cs.xlog.dao.impl.MessageDaoImpl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.social.twitter.api.DirectMessage;
import org.springframework.stereotype.Service;

import com.googlecode.gmail4j.javamail.JavaMailGmailMessage;

@Service
public class MessageClient {
	@Inject
	private MessageAdapter messageAdapter;
	@Inject
	private MessageDaoImpl messageDaoImpl;

	public void saveOrUpdateTwitterMessages(String userName, List<DirectMessage> messages) {
		messageDaoImpl.saveOrUpdateAll(messageAdapter.twitterMessageListAdapter(userName, messages));

	}

	public void saveOrUpdateGmailMessages(String userName, List<JavaMailGmailMessage> message) {
		messageDaoImpl.saveOrUpdateAll(messageAdapter.gmailMessageListAdapter(userName, message));

	}
	
	
}
