package hk.hku.cs.xlog.bo;

import java.util.List;

/**
 * @author Gerald.xv at gmail.com
 * @version 创建时间：2012-6-22 下午2:29:59
 */
public interface AccountClient {

	void saveProfile(String userName, String serviceProvider);

	List<String> getProfiles(String userName);

	String getProfile(String userName);

}
