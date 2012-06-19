package hk.hku.cs.xlog.bo;

import hk.hku.cs.xlog.entity.Tag;

import java.util.ArrayList;

public interface StatusItemClient {

	void reply(String servicePovider, String statusId, String content);

	void markFav(String statusId);

	void removeFav(String statusId);

	void share(String servicePovider, String statusId, String content);

	void delete(String statusId);

	void addTags(String statusId, String Tags);

	ArrayList<Tag> showTags(String idAtservice);

	

}
