package nstarlike.jcw.dao;

import java.util.List;

import nstarlike.jcw.model.User;

public interface UserDao {
	public List<User> readAll();
	public User readByUserId(Long userId);
	public User readByLoginId(String loginId);
	public User readByEmail(String email);
	public int create(User user);
	public int update(User user);
	public int delete(Long userId);
}
