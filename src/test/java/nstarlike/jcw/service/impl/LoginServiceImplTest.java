package nstarlike.jcw.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import nstarlike.jcw.model.User;
import nstarlike.jcw.service.LoginService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {
	"classpath:datasource-context.xml", 
	"classpath:security-context.xml", 
	"classpath:aop-context.xml", 
	"file:src/main/webapp/WEB-INF/app-context.xml"
})
@Transactional
class LoginServiceImplTest {
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImplTest.class);
	
	@Autowired
	private LoginService loginService;
	
	@Test
	public void testSearchLoginId() throws Exception {
		logger.debug("start UserDetailsServiceImplTest.testSearchId");
		
		User user = new User();
		user.setName("name1");
		user.setEmail("test1@naver.com");
		
		String loginId = loginService.searchLoginId(user);
		
		assertNotNull(loginId);
		assertTrue(loginId.equals("test1"));
	}
	
	@Test
	@Disabled
	public void testResetPassword() {
		logger.debug("start UserDetailsServiceImplTest.testResetPassword");
		
		User user = new User();
		user.setLoginId("test1");
		user.setName("name1");
		user.setEmail("test1@naver.com");
		
		boolean ret = loginService.resetPassword(user);
		
		assertTrue(ret);
	}
}
