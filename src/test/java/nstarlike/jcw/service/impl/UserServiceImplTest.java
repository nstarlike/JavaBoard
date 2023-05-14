package nstarlike.jcw.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import nstarlike.jcw.service.UserService;
import nstarlike.jcw.model.User;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {
	"classpath:datasource-context.xml", 
	"classpath:security-context.xml", 
	"file:src/main/webapp/WEB-INF/app-context.xml"
})
@Transactional
class UserServiceImplTest {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);
	
	@Autowired
	private UserService userService;
	
	@Test
	void testGetAll() {
		logger.debug("start UserServiceImplTest.testGetAll()");
		
		Map<String, String> params;
		List<User> list;
		
		//test if all list is returned.
		params = new HashMap<>();
		params.put("startNo", "0");
		params.put("endNo", "10");
		
		logger.debug("params for all list=" + params);
		
		list = userService.getAll(params);
		
		assertNotNull(list);
		
		logger.debug("all list=>");
		for(User user : list) {
			logger.debug(user.toString());
		}
		
		//test if searched list by conditions is returned.
		params = new HashMap<>();
		params.put("startNo", "0");
		params.put("endNo", "10");
		params.put("loginId", "test1");
		params.put("name", "name1");
		params.put("email", "test1@naver.com");
		
		logger.debug("params for searched list=" + params);
		
		list = userService.getAll(params);
		
		assertNotNull(list);
		
		logger.debug("searched list=>");
		for(User user : list) {
			logger.debug(user.toString());
		}
		
		assertTrue(list.get(0).getLoginId().equals("test1"));
	}

	@Test
	void testGetById() {
		logger.debug("start UserServiceImplTest.testGetById()");
		
		long id = 2L;
		User user = userService.getById(id);
		
		assertNotNull(user);
		
		logger.debug("user=" + user);
		
		assertTrue(user.getId() == id);
	}

	@Test
	void testGetByLoginId() {
		logger.debug("start UserServiceImplTest.testGetByLoginId()");
		
		String loginId = "test1";
		User user = userService.getByLoginId(loginId);
		
		assertNotNull(user);
		
		logger.debug("user=" + user);
		
		assertTrue(user.getLoginId().equals(loginId));
	}

	@Test
	void testGetByEmail() {
		logger.debug("start UserServiceImplTest.testGetByEmail()");
		
		String email = "test1@naver.com";
		User user = userService.getByEmail(email);
		
		assertNotNull(user);
		
		logger.debug("user=" + user);
		
		assertTrue(user.getEmail().equals(email));
	}

	@Test
	void testCreate() {
		logger.debug("start UserServiceImplTest.testCreate()");
		
		String loginId = "unit1";
		String email = "unit1@naver.com";
		
		User user = new User();
		user.setLoginId(loginId);
		user.setPassword("password");
		user.setEmail(email);
		user.setName("name1");
		
		logger.debug("user=" + user);
		
		int ret = userService.create(user);
		
		assertTrue(ret > 0);
		
		User created = userService.getByLoginId(loginId);
		
		assertNotNull(created);
		
		logger.debug("created user=" + created);
		
		assertTrue(created.getEmail().equals(email));
	}

	@Test
	void testUpdate() {
		logger.debug("start UserServiceImplTest.testUpdate()");
		
		long id = 2L;
		
		User user = new User();
		user.setId(id);
		user.setName("updatedname");
		
		logger.debug("user=" + user);
		
		int ret = userService.update(user);
		
		assertTrue(ret > 0);
		
		User updated = userService.getById(id);
		
		assertNotNull(updated);

		logger.debug("updated user=" + updated);
		
		assertTrue(updated.getId() == id);
		
	}

	@Test
	void testDelete() {
		logger.debug("start UserServiceImplTest.testDelete()");
		
		long id = 2L;
		
		int ret = userService.delete(id);
		
		assertTrue(ret > 0);
		
		User deleted = userService.getById(id);
		
		assertNull(deleted);
	}

}
