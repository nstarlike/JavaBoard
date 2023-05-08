package nstarlike.jcw.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import nstarlike.jcw.dao.UserDao;
import nstarlike.jcw.model.User;
import nstarlike.jcw.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	@Override
	public List<User> getAll() {
		return userDao.readAll();
	}

	@Override
	public User getByUserId(Long userId) {
		return userDao.readByUserId(userId);
	}

	@Override
	public User getByLoginId(String loginId) {
		return userDao.readByLoginId(loginId);
	}

	@Override
	public User getByEmail(String email) {
		return userDao.readByEmail(email);
	}

	@Override
	public int create(User user) {
		return userDao.create(user);
	}

	@Override
	public int update(User user) {
		return userDao.update(user);
	}

	@Override
	public int delete(Long userId) {
		return userDao.delete(userId);
	}

}
