package hk.hku.cs.xlog.entity;

public class GmailAccount {
	private String refUser;
	private String account;
	private String password;

	public GmailAccount() {
	}

	public GmailAccount(String refUser, String account, String password) {
		this.refUser = refUser;
		this.account = account;
		this.password = password;
	}

	public String getRefUser() {
		return refUser;
	}

	public void setRefUser(String refUser) {
		this.refUser = refUser;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
