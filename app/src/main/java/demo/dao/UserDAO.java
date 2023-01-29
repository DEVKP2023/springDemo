package demo.dao;

import java.util.List;

import demo.model.User;

public interface UserDAO {
	
	User getUser(int id);
	List<User> getAllUsers();
	int createUser(User user);
	int updateUser(User user);
	int deleteUser(int id);

}
