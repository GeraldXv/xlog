package hk.hku.cs.xlog.dao.impl;

import hk.hku.cs.xlog.dao.MessageDao;
import hk.hku.cs.xlog.entity.Message;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class MessageDaoImpl extends HibernateDaoSupport implements MessageDao {

	@Override
	public Message get(String idAtService) {

		return (Message) getHibernateTemplate().get(Message.class, idAtService);
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
		getHibernateTemplate().saveOrUpdateAll(messages);

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

		return (List<Message>) getHibernateTemplate().find(
				"from Message where refUser=?", refName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getMessagesByTime(String refName, String gmailAccount,
			String twitterAccount) {
		getHibernateTemplate().setMaxResults(13);
		return (List<Message>) getHibernateTemplate()
				.find("from Message where refUser=? and (fromEmail <> ? or fromId <> ?) order by createdDate desc ",
						new Object[] { refName, gmailAccount, twitterAccount });
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getMessagesByUserName(String refName, String fromUser) {
		getHibernateTemplate().setMaxResults(10);
		return (List<Message>) getHibernateTemplate()
				.find("from Message where refUser=? and (fromName=? or toName=?) order by createdDate desc ",
						new Object[] { refName, fromUser, fromUser });
	}

}
