package nstarlike.jcw.dao;

import java.util.List;
import java.util.Map;

import nstarlike.jcw.model.User;

public interface UserDao {
	public List<User> readAll(Map<String, String> params);
	public User readById(Long id);
	public User readByLoginId(String loginId);
	public User readByEmail(String email);
	public int create(User user);
	public int update(User user);
	public int delete(Long id);
}
