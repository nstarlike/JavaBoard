package nstarlike.jcw.user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

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
	@Autowired
	private UserService userService;
	
	@Test
	void testGetAll() {
		Map<String, String> params;
		List<User> list;
		
		//test if all list is returned.
		params = new HashMap<>();
		params.put("startNo", "0");
		params.put("endNo", "10");
		
		list = userService.getAll(params);
		assertNotNull(list);
		
		//test if searched list by conditions is returned.
		params = new HashMap<>();
		params.put("startNo", "0");
		params.put("endNo", "10");
		params.put("loginId", "test1");
		params.put("name", "name1");
		params.put("email", "test1@naver.com");
		
		list = userService.getAll(params);
		assertNotNull(list);
		assertTrue(list.get(0).getLoginId().equals("test1"));
	}

	@Test
	void testGetById() {
		long id = 2L;
		User user = userService.getById(id);
		
		assertNotNull(user);
		assertTrue(user.getId() == id);
	}

	@Test
	void testGetByLoginId() {
		String loginId = "test1";
		User user = userService.getByLoginId(loginId);
		
		assertNotNull(user);
		assertTrue(user.getLoginId().equals(loginId));
	}

	@Test
	void testGetByEmail() {
		String email = "test1@naver.com";
		User user = userService.getByEmail(email);
		
		assertNotNull(user);
		assertTrue(user.getEmail().equals(email));
	}

	@Test
	void testCreate() {
		String loginId = "unit1";
		String email = "unit1@naver.com";
		
		User user = new User();
		user.setLoginId(loginId);
		user.setPassword("password");
		user.setEmail(email);
		user.setName("name1");
		
		int ret = userService.create(user);
		
		User created = userService.getByLoginId(loginId);
		
		assertTrue(ret > 0);
		assertTrue(created.getEmail().equals(email));
	}

	@Test
	void testUpdate() {
		long id = 2L;
		
		User user = new User();
		user.setId(id);
		user.setName("updatedname");
		
		int ret = userService.update(user);
		
		User updated = userService.getById(id);
		
		assertTrue(ret > 0);
		assertTrue(updated.getId() == id);
	}

	@Test
	void testDelete() {
		long id = 2L;
		
		int ret = userService.delete(id);
		
		User deleted = userService.getById(id);
		
		assertTrue(ret > 0);
		assertNull(deleted);
	}

}