package hk.hku.cs.xlog.gmail;

import java.util.List;
import org.springframework.stereotype.Service;
import com.googlecode.gmail4j.GmailMessage;
import com.googlecode.gmail4j.auth.Credentials;
import com.googlecode.gmail4j.javamail.ImapGmailConnection;
import com.googlecode.gmail4j.javamail.ImapGmailLabel;
import com.googlecode.gmail4j.javamail.JavaMailGmailMessage;
@Service

public class GmailClientX {
	ImapGmailConnection connection = new ImapGmailConnection();
	GmailTemplate client = new GmailTemplate(ImapGmailLabel.IMPORTANT);

	public List<JavaMailGmailMessage> getMessage(String userName, String password) {

		Credentials gmailCredential = new Credentials(userName, password.toCharArray());
		connection.setLoginCredentials(gmailCredential);
		client.setConnection(connection);

		return client.getAllMessages();

	}

	public List<JavaMailGmailMessage> getRecentMessages(String userName, String password) {
		Credentials gmailCredential = new Credentials(userName, password.toCharArray());
		connection.setLoginCredentials(gmailCredential);
		client.setConnection(connection);
		return client.getRecentMessages();

	}

	public List<GmailMessage> getUnreadMessage(String userName, String password) {
		Credentials gmailCredential = new Credentials(userName, password.toCharArray());
		connection.setLoginCredentials(gmailCredential);
		client.setConnection(connection);
		return client.getUnreadMessages();

	}

	public void sendMessage(String userName, String password, GmailMessage gmessage) {
		Credentials gmailCredential = new Credentials(userName, password.toCharArray());
		connection.setLoginCredentials(gmailCredential);
		client.setConnection(connection);
		client.send(gmessage);
	}

}
