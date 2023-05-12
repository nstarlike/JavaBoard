package nstarlike.jcw.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import nstarlike.jcw.model.User;
import nstarlike.jcw.security.UserPrincipal;
import nstarlike.jcw.dao.UserDao;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("start UserDetailsServiceImpl.loadUserByUsername()");
		logger.debug("username=" + username);
		
		User user = userDao.readByLoginId(username);
		
		logger.debug("user=" + user);
		
		return new UserPrincipal(user);
	}

}
