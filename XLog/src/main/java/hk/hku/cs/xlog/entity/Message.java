package hk.hku.cs.xlog.entity;

import java.util.Date;

public class Message {
	private String idAtService;
	private String serviceProvider;
	private String refUser;
	private String fromName;
	private String fromEmail;
	private String fromId;
	private String fromProfileImage;
	private String toName;
	private String toEmail;
	private String toId;
	private String toProfileImage;
	private String content;
	private Date createdDate;
	private String subject;
	private String attachedFile;

	public String getIdAtService() {
		return idAtService;
	}

	public void setIdAtService(String idAtService) {
		this.idAtService = idAtService;
	}

	public String getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public String getRefUser() {
		return refUser;
	}

	public void setRefUser(String refUser) {
		this.refUser = refUser;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	public String getFromProfileImage() {
		return fromProfileImage;
	}

	public void setFromProfileImage(String fromProfileImage) {
		this.fromProfileImage = fromProfileImage;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getToId() {
		return toId;
	}

	public void setToId(String toId) {
		this.toId = toId;
	}

	public String getToProfileImage() {
		return toProfileImage;
	}

	public void setToProfileImage(String toProfileImage) {
		this.toProfileImage = toProfileImage;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAttachedFile() {
		return attachedFile;
	}

	public void setAttachedFile(String attachedFile) {
		this.attachedFile = attachedFile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAtService == null) ? 0 : idAtService.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (idAtService == null) {
			if (other.idAtService != null)
				return false;
		} else if (!idAtService.equals(other.idAtService))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Message [idAtService=" + idAtService + ", serviceProvider=" + serviceProvider + ", refUser=" + refUser + ", fromName=" + fromName
				+ ", fromEmail=" + fromEmail + ", fromId=" + fromId + ", fromProfileImage=" + fromProfileImage + ", toName=" + toName + ", toEmail=" + toEmail
				+ ", toId=" + toId + ", toProfileImage=" + toProfileImage + ", content=" + content + ", createdDate=" + createdDate + ", subject=" + subject
				+ ", attachedFile=" + attachedFile + "]";
	}

}
