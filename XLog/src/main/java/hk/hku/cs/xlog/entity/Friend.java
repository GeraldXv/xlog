package hk.hku.cs.xlog.entity;

public class Friend {

	private String refUser;

	private String idAtService;// For Service

	private String serviceProvider;

	private String username;

	private String name;

	private String screenName;// Screen = First Name+ Last Name

	private String gender;// No gender in Twitter

	private String homeLink;// To a specific Service

	private String email;

	private String imageUrl;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHomeLink() {
		return homeLink;
	}

	public void setHomeLink(String homeLink) {
		this.homeLink = homeLink;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "Profile [" + "serviceProvider=" + serviceProvider
				+ ", username=" + username + ", name=" + name + ", screenName="
				+ screenName + ", gender=" + gender + ", homeLink=" + homeLink
				+ ", email=" + email + "]";
	}

	public String getRefUser() {
		return refUser;
	}

	public void setRefUser(String refUser) {
		this.refUser = refUser;
	}

}
