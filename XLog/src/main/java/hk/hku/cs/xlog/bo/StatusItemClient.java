package hk.hku.cs.xlog.bo;

public interface StatusItemClient {

	void reply(String servicePovider, String userId, String statusId,
			String content);

	void markFav(String statusId, String userId);

	void removeFav(String statusId, String userId);

	void share(String servicePovider, String userId, String statusId,
			String content);

	void delete(String statusId, String userId);

	void addTags(String statusId, String Tags);// TODO

	void showTags();// TODO

	void showFullContent(String statusId);// Message Can be large

	void hiddenFullContent(String statusId);

}
