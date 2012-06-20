package hk.hku.cs.xlog.adapter;

import hk.hku.cs.xlog.entity.Message;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.social.twitter.api.DirectMessage;
import org.springframework.stereotype.Service;

import com.googlecode.gmail4j.GmailException;
import com.googlecode.gmail4j.javamail.JavaMailGmailMessage;

@Service
public class MessageAdapter {

	private Message message;
	private List<Message> messageList;

	public Message GmailMessageAdapter(JavaMailGmailMessage gMessage) {
		if (gMessage.getPreview().startsWith("javax.mail.internet.MimeMultipart"))
			return null;

		message = new Message();
		message.setFromName(gMessage.getFrom().getName());
		message.setFromEmail(gMessage.getFrom().getEmail());
		if (gMessage.getFrom().getName() == null)
			message.setFromName(gMessage.getFrom().getEmail());
		message.setToEmail(gMessage.getTo().get(0).getEmail());
		message.setToName(gMessage.getTo().get(0).getName());
		if (gMessage.getTo().get(0).getName() == null)
			message.setToName(gMessage.getTo().get(0).getEmail());
		message.setCreatedDate(gMessage.getSendDate());
		message.setServiceProvider("gmail");
		message.setContent(gMessage.getPreview());
		message.setIdAtService("" + gMessage.getSendDate().getTime());

		return message;
	}

	public Message twitterMessageAdapter(DirectMessage tMessage) {
		message = new Message();
		message.setIdAtService("" + tMessage.getId());
		message.setServiceProvider("twitter");
		message.setFromName(tMessage.getSender().getName());
		message.setFromId("" + tMessage.getSender().getId());
		message.setFromProfileImage(tMessage.getSender().getProfileImageUrl());
		message.setToId("" + tMessage.getRecipient().getId());
		message.setToName("" + tMessage.getRecipient().getName());
		message.setToProfileImage(tMessage.getRecipient().getProfileImageUrl());
		message.setCreatedDate(tMessage.getCreatedAt());
		message.setContent(tMessage.getText());
		return message;

	}

	public List<Message> gmailMessageListAdapter(String userName, List<JavaMailGmailMessage> gMessageList) {
		messageList = new ArrayList<Message>();
		for (JavaMailGmailMessage gm : gMessageList) {
			Message m;
			try {
				m = GmailMessageAdapter(gm);
			} catch (GmailException e) {
				m = null;
			} catch (NullPointerException ex) {
				m = null;
			}
			if (m != null) {
				m.setMessageId(m.getIdAtService() + userName);
				m.setRefUser(userName);
				messageList.add(m);
			}
		}
		Set<Message> set = new HashSet<Message>(messageList);
		messageList.clear();
		messageList = new ArrayList<Message>(set);
		return messageList;
	}

	public List<Message> twitterMessageListAdapter(String userName, List<DirectMessage> tMessageList) {
		messageList = new ArrayList<Message>();
		for (DirectMessage dm : tMessageList) {
			Message tm = twitterMessageAdapter(dm);
			tm.setRefUser(userName);
			tm.setMessageId(tm.getIdAtService() + userName);
			messageList.add(tm);
		}
		return messageList;
	}

}
