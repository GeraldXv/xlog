package hk.hku.cs.xlog.dao;

import hk.hku.cs.xlog.entity.User;
import hk.hku.cs.xlog.exception.UsernameAlreadyInUseException;

import java.util.List;

public interface UserDao {

	User get(Integer id);

	User getByUserName(String userName);

	Integer save(User user) throws UsernameAlreadyInUseException;

	void update(User user);

	void delete(User user);

	void delete(Integer id);

	List<User> getAll();

}
