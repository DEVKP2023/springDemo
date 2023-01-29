package demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import demo.model.User;

@Component
public class UserDAOImpl implements UserDAO{
	
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UserRowMapper userRowMapper;

	@Override
	public int createUser(User user) {
		Map<String, String> userMap=new HashMap<>();
		userMap.put("FN", user.getFirstName());
		userMap.put("LN", user.getLastName());
		return namedParameterJdbcTemplate.update("INSERT INTO users(firstName,lastName) VALUES (:FN, :LN)",userMap);
	}

	@Override
	public int updateUser(User user) {
		Map<String, Object> userMap=new HashMap<>();
		userMap.put("fn", user.getFirstName());
		userMap.put("ln", user.getLastName());
		userMap.put("id", user.getId());
		return namedParameterJdbcTemplate.update("UPDATE users SET firstName=:fn, lastName=:ln WHERE id=:id",userMap);
	}

	@Override
	public int deleteUser(int id) {
		Map<String, Object> userMap=new HashMap<>();
		userMap.put("id", id);
		return namedParameterJdbcTemplate.update("DELETE from users WHERE id=:id", userMap);
	}

	@Override
	public User getUser(int id) {
		Map<String, Integer> paramMap=new HashMap<>();
		paramMap.put("id", id);
		try {
			return namedParameterJdbcTemplate.queryForObject("SELECT id, firstName, lastName FROM users where id=:id", paramMap, userRowMapper);
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<User> getAllUsers() {
		return jdbcTemplate.query("SELECT id, firstName, lastName FROM users", userRowMapper);
	}
	
	

}
