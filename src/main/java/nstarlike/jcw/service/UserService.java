package nstarlike.jcw.service;

import java.util.List;

import nstarlike.jcw.model.User;

public interface UserService {
	public List<User> getAll();
	public User getById(Long id);
	public User getByLoginId(String loginId);
	public User getByEmail(String email);
	public int create(User user);
	public int update(User user);
	public int delete(Long id);
}
