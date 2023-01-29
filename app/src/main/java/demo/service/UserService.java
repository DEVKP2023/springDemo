package demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.dao.UserDAO;
import demo.model.User;

@Component
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	public List<User> getAllUsers(){
		return userDAO.getAllUsers();
	}
	
	public int createUser(User user) {
		return userDAO.createUser(user);
	}
	
	public User getUser(int id) {
		if(id>0) {
			return userDAO.getUser(id);
		}
		return null;
	}

	public int updateUser(User user) {
		return userDAO.updateUser(user);
	}

	public int deleteUser(int id) {
		return userDAO.deleteUser(id);
	}

}
