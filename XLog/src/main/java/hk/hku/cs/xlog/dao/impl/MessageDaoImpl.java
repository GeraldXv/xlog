package hk.hku.cs.xlog.dao.impl;

import hk.hku.cs.xlog.dao.MessageDao;
import hk.hku.cs.xlog.entity.Message;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class MessageDaoImpl extends HibernateDaoSupport implements MessageDao {

	@Override
	public Message get(String id) {
		return (Message) getHibernateTemplate().get(Message.class, id);
	}

	@Override
	public void save(Message message) {
		getHibernateTemplate().save(message);

	}

	@Override
	public void update(Message message) {
		getHibernateTemplate().update(message);

	}

	@Override
	public void saveOrUpdateAll(List<Message> messages) {
		for (Message m : messages)
			getHibernateTemplate().merge(m);

	}

	@Override
	public void delete(Message message) {
		getHibernateTemplate().delete(message);

	}

	@Override
	public void delete(String idAtService) {
		getHibernateTemplate().delete(get(idAtService));

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getMessages(String refName) {

		return (List<Message>) getHibernateTemplate().find("from Message where refUser=?", refName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getMessagesByTime(String refName, String gmailAccount, String twitterAccount) {
		getHibernateTemplate().setMaxResults(13);
		return (List<Message>) getHibernateTemplate().find("from Message where refUser=? and (fromEmail <> ? or fromId <> ?) order by createdDate desc ",
				new Object[] { refName, gmailAccount, twitterAccount });
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getMessagesByUserName(String refName, String fromUser) {
		getHibernateTemplate().setMaxResults(10);
		return (List<Message>) getHibernateTemplate().find("from Message where refUser=? and (fromName=? or toName=?) order by createdDate desc ",
				new Object[] { refName, fromUser, fromUser });
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getUnreadGmail(String refUser) {

		return ((List<Message>) getHibernateTemplate().find("from Message where refUser=? and xread=0 and serviceProvider=gmail", new Object[] { refUser }))
				.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getUnreadTwitterMessage(String refUser) {
		return ((List<Message>) getHibernateTemplate().find("from Message where refUser=? and xread=0 and serviceProvider=twitter", new Object[] { refUser }))
				.size();

	}

	@Override
	public void markAllRead(String refUser) {
		@SuppressWarnings("unchecked")
		List<Message> mlist = (List<Message>) getHibernateTemplate().find("from Message where xread=0 and refUser=?", refUser);
		for (Message m : mlist) {
			m.setXread(true);
		}
		getHibernateTemplate().saveOrUpdateAll(mlist);
	}

}
