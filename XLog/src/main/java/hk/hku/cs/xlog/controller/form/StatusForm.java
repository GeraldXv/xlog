package hk.hku.cs.xlog.controller.form;

/**
 * @author Gerald.xv at gmail.com
 */
public class StatusForm {

	private String text;
	private String[] provider;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String[] getProvider() {
		return provider;
	}

	public void setProvider(String[] provider) {
		this.provider = provider;
	}

}
