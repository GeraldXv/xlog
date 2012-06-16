package hk.hku.cs.xlog.controller.form;

/**
 * @author Gerald.xv at gmail.com
 * @version 创建时间：2012-6-16 下午3:51:59 类说明
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
