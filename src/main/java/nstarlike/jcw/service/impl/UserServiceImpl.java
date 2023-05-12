package nstarlike.jcw.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nstarlike.jcw.dao.UserDao;
import nstarlike.jcw.model.User;
import nstarlike.jcw.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public List<User> getAll(Map<String, String> params) {
		logger.debug("start UserServiceImpl.getAll()");
		logger.debug("params=" + params);
		
		return userDao.readAll(params);
	}

	@Override
	public User getById(Long id) {
		logger.debug("start UserServiceImpl.getById()");
		logger.debug("id=" + id);
		
		return userDao.readById(id);
	}

	@Override
	public User getByLoginId(String loginId) {
		logger.debug("start UserServiceImpl.getByLoginId()");
		logger.debug("loginId=" + loginId);
		
		return userDao.readByLoginId(loginId);
	}

	@Override
	public User getByEmail(String email) {
		logger.debug("start UserServiceImpl.getByEmail()");
		logger.debug("email=" + email);
		
		return userDao.readByEmail(email);
	}

	@Override
	public int create(User user) {
		logger.debug("start UserServiceImpl.create()");
		logger.debug("user=" + user);
		
		return userDao.create(user);
	}

	@Override
	public int update(User user) {
		logger.debug("start UserServiceImpl.update()");
		logger.debug("user=" + user);
		
		return userDao.update(user);
	}

	@Override
	public int delete(Long id) {
		logger.debug("start UserServiceImpl.delete()");
		logger.debug("id=" + id);
		
		return userDao.delete(id);
	}

}
