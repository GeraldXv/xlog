package hk.hku.cs.xlog.dao;

import hk.hku.cs.xlog.entity.Status;
import hk.hku.cs.xlog.entity.Tag;

import java.util.List;

public interface StatusDao {

	Status get(String statusId);

	void save(Status status);

	void update(Status status);

	void saveOrUpdateAll(List<Status> status);

	void delete(Status status);

	void delete(String statusId);
	
	List<Tag> getTagsbyIdAtservice(String idAtService);

	List<Status> getStatus(String refName);

	List<Status> getStatusAllByTime(String refName);

	List<Status> getStatusAllByTimeAndService(String refName, String service);

}
