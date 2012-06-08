package hk.hku.cs.xlog.adapter;

import hk.hku.cs.xlog.entity.Message;
import hk.hku.cs.xlog.gmail.GmailClientX;

import java.util.List;

import org.junit.Test;

import com.googlecode.gmail4j.javamail.JavaMailGmailMessage;

public class MessageAdapterTest {

	MessageAdapter messageAdapter = new MessageAdapter();

	@Test
	public void test() {
		GmailClientX Gc = new GmailClientX();
		List<JavaMailGmailMessage> mlist = Gc.getMessage("Gerald.Xv", "pkpbxjg0");

		List<Message> mXList = messageAdapter.gmailMessageListAdapter("GeraldXv", mlist);
		for (Message MM : mXList) {
			System.out.println(MM.toString());
		}
	}

}
