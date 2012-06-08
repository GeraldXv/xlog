package hk.hku.cs.xlog.gmail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.googlecode.gmail4j.GmailException;
import com.googlecode.gmail4j.javamail.JavaMailGmailMessage;

public class GmailClientXTest {

	@Test
	public void test() {
		GmailClientX Gc = new GmailClientX();
		List<JavaMailGmailMessage> mlist = Gc.getMessage("username", "password");
		List<JavaMailGmailMessage> nList = new ArrayList<JavaMailGmailMessage>();
		System.out.println(mlist.size());
		for (JavaMailGmailMessage m : mlist) {
			// if
			// (!m.getPreview().startsWith("javax.mail.internet.MimeMultipart"))
			// {
			// nList.add(m);
			// }

			try {
				System.out.println(m.getFrom().getName() + m.getSendDate().getTime() + m.getTo().get(0).getEmail());
			} catch (GmailException e) {
				System.out.println(m.getFrom().getName() + m.getSendDate().getTime() + "gerald.xv@gmail.com");
			}

		}

		System.out.println(nList.size());
	}
}
