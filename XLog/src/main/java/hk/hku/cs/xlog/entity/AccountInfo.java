package hk.hku.cs.xlog.entity;

public class AccountInfo {

	private int id;
	private String pictureUrl;
	private int laguage;// 0: English 1: Chinese...
	private int pageSize;

	public AccountInfo(int id, String pictureUrl, int laguage, int pageSize) {
		super();
		this.id = id;
		this.pictureUrl = pictureUrl;
		this.laguage = laguage;
		this.pageSize = pageSize;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public int getLaguage() {
		return laguage;
	}

	public void setLaguage(int laguage) {
		this.laguage = laguage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "AccountInfo [id=" + id + ", pictureUrl=" + pictureUrl
				+ ", laguage=" + laguage + ", pageSize=" + pageSize + "]";
	}

}
