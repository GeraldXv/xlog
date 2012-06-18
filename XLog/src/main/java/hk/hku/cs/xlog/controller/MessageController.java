package hk.hku.cs.xlog.controller;

import hk.hku.cs.xlog.bo.impl.NotificationClientImpl;
import hk.hku.cs.xlog.controller.form.MessageForm;
import hk.hku.cs.xlog.dao.GmailAccountDao;
import hk.hku.cs.xlog.dao.MessageDao;
import hk.hku.cs.xlog.dao.UserConnectionDao;
import hk.hku.cs.xlog.dao.UserDao;
import hk.hku.cs.xlog.entity.GmailAccount;
import hk.hku.cs.xlog.entity.Message;
import hk.hku.cs.xlog.gmail.GmailClientX;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.googlecode.gmail4j.EmailAddress;
import com.googlecode.gmail4j.GmailMessage;
import com.googlecode.gmail4j.javamail.JavaMailGmailMessage;

@Controller
@RequestMapping("/message")
public class MessageController {

	private MessageDao messageDaoImpl;
	private GmailAccountDao gmailAccountDaoImpl;
	private UserDao userDaoImpl;
	private UserConnectionDao userConnectionDaoImpl;
	@Inject
	Twitter twitter;
	@Inject
	NotificationClientImpl notificationClientImpl;

	@Inject
	public MessageController(MessageDao messageDaoImpl, GmailAccountDao gmailAccountDaoImpl, UserDao userDaoImpl, UserConnectionDao userConnectionDaoImpl) {
		this.messageDaoImpl = messageDaoImpl;
		this.gmailAccountDaoImpl = gmailAccountDaoImpl;
		this.userDaoImpl = userDaoImpl;
		this.userConnectionDaoImpl = userConnectionDaoImpl;

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String services(Principal currentUser, Model model) {
		List<Message> mList = messageDaoImpl.getMessagesByTime(currentUser.getName(), gmailAccountDaoImpl.getByUserName(currentUser.getName()).getAccount(),
				userConnectionDaoImpl.getByNameAndProvider(currentUser.getName(), "twitter").getUserConPK().getProviderUserId());
		List<Message> newList = new ArrayList<Message>();
		for (int i = 0; i < mList.size(); i++) {
			boolean flag = false;
			for (int j = 0; j < i; j++) {
				if (mList.get(i).getFromName().equals(mList.get(j).getFromName()))
					flag = true;
			}
			if (!flag) {
				newList.add(mList.get(i));
			}
		}
		model.addAttribute("friendList", newList);
		model.addAttribute("messages", messageDaoImpl.getMessagesByUserName(currentUser.getName(), mList.get(0).getFromName()));
		model.addAttribute("fromUser", mList.get(0).getFromName());
		model.addAttribute("profileImage", userDaoImpl.getByUserName(currentUser.getName()).getProfileImage());
		model.addAttribute("messageForm", new MessageForm());
		notificationClientImpl.MakeAllMessageRead(currentUser.getName());
		model.addAttribute("messageNotification", notificationClientImpl.getNotification(currentUser.getName()));
		return "message";

	}

	@RequestMapping(value = "/{fromUser}", method = RequestMethod.GET)
	public String services(Principal currentUser, Model model, @PathVariable String fromUser) {
		List<Message> mList = messageDaoImpl.getMessagesByTime(currentUser.getName(), gmailAccountDaoImpl.getByUserName(currentUser.getName()).getAccount(),
				userConnectionDaoImpl.getByNameAndProvider(currentUser.getName(), "twitter").getUserConPK().getProviderUserId());
		List<Message> newList = new ArrayList<Message>();
		for (int i = 0; i < mList.size(); i++) {
			boolean flag = false;
			for (int j = 0; j < i; j++) {
				if (mList.get(i).getFromName().equals(mList.get(j).getFromName()))
					flag = true;
			}
			if (!flag) {
				newList.add(mList.get(i));
			}
		}
		model.addAttribute("friendList", newList);
		model.addAttribute("messages", messageDaoImpl.getMessagesByUserName(currentUser.getName(), fromUser));
		model.addAttribute("fromUser", fromUser);
		model.addAttribute("profileImage", userDaoImpl.getByUserName(currentUser.getName()).getProfileImage());
		model.addAttribute("messageForm", new MessageForm());
		return "message";
	}

	@RequestMapping(value = "/send/{providerId}", method = RequestMethod.POST)
	public String addService(Principal currentUser, @PathVariable String providerId, MessageForm messageForm) {
		if (providerId.equals("twitter")) {
			twitter.directMessageOperations().sendDirectMessage(messageForm.getTo(), messageForm.getText());
		} else if (providerId.equals("gmail")) {
			GmailAccount gaccount = gmailAccountDaoImpl.getByUserName(currentUser.getName());
			GmailMessage gmessage = new JavaMailGmailMessage();
			gmessage.setContentText(messageForm.getText());
			EmailAddress from = new EmailAddress(currentUser.getName(), gaccount.getAccount() + "@gmail.com");
			EmailAddress to = new EmailAddress(messageForm.getTo());
			gmessage.setFrom(from);
			gmessage.setSubject(messageForm.getSubject());
			gmessage.addTo(to);
			GmailClientX Gc = new GmailClientX();
			Gc.sendMessage(gaccount.getAccount(), gaccount.getPassword(), gmessage);
		}
		return "redirect:/message/";

	}
}
