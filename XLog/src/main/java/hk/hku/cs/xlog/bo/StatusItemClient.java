package hk.hku.cs.xlog.bo;

import hk.hku.cs.xlog.controller.form.TagContainner;

public interface StatusItemClient {

	void reply(String servicePovider, String statusId, String content);

	void markFav(String statusId);

	void removeFav(String statusId);

	void share(String servicePovider, String statusId, String content);

	void delete(String statusId);

	void addTags(String statusId, String Tags);

	TagContainner showTags(String idAtservice,String fromUser);

	

}
