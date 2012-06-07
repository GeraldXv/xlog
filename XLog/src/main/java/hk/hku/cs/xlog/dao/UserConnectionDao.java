package hk.hku.cs.xlog.dao;

import hk.hku.cs.xlog.entity.UserConnection;

import java.util.List;

public interface UserConnectionDao {

	List<UserConnection> getByName(String refUser);

	UserConnection getByNameAndProvider(String refUser, String providerId);

}
