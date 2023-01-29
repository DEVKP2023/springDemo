package demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.model.User;
import demo.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	// http://localhost:8080/app/user
	// provide json in request body
	// sample json: {"firstName":"FN1", "lastName":"LN1"}
	@RequestMapping(method = RequestMethod.PUT, path = "/user")
	@ResponseBody
	public int createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	// http://localhost:8080/app/users
	@RequestMapping(method = RequestMethod.GET, path = "/users")
	@ResponseBody
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	// http://localhost:8080/app/user?id=1
	@RequestMapping(method = RequestMethod.GET, path = "/user")
	@ResponseBody
	public User gelUsers(@RequestParam(name = "id") int id) {
		return userService.getUser(id);
	}

	// http://localhost:8080/app/users
	// provide json in request body
	// sample json: {"firstName":"Kelly", "lastName":"Ford", "id":4}
	@RequestMapping(method = RequestMethod.POST, path = "/user")
	@ResponseBody
	public int updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}

	// http://localhost:8080/app/user?id=3
	@RequestMapping(method = RequestMethod.DELETE, path = "/user")
	@ResponseBody
	public int deleteUser(@RequestParam int id) {
		return userService.deleteUser(id);
	}

}
