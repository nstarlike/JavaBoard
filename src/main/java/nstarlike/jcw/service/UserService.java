package nstarlike.jcw.service;

import java.util.List;
import java.util.Map;

import nstarlike.jcw.model.User;

public interface UserService {
	public List<User> getAll(Map<String, String> params);
	public User getById(Long id);
	public User getByLoginId(String loginId);
	public User getByEmail(String email);
	public int create(User user);
	public int update(User user);
	public int delete(Long id);
}
