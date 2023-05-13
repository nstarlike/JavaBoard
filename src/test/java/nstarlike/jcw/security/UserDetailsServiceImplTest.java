package nstarlike.jcw.security;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import nstarlike.jcw.model.User;
import nstarlike.jcw.service.impl.UserDetailsServiceImpl;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {
	"classpath:datasource-context.xml", 
	"classpath:security-context.xml", 
	"file:src/main/webapp/WEB-INF/app-context.xml"
})
public class UserDetailsServiceImplTest {
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImplTest.class);
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Test
	public void testLoadUserByUsername() {
		logger.debug("start " + this.getClass().getSimpleName() + ".testLoadUserByUsername()");
		
		String username = "test1";
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		
		assertNotNull(userDetails);
		assertTrue(userDetails instanceof UserPrincipal);
		
		logger.debug("userDetails=" + userDetails);
	}
	
	@Test
	public void testSearchLoginId() throws Exception {
		logger.debug("start UserDetailsServiceImplTest.testSearchId");
		
		User user = new User();
		user.setName("name1");
		user.setEmail("test1@naver.com");
		
		String loginId = userDetailsService.searchLoginId(user);
		
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
		
		boolean ret = userDetailsService.resetPassword(user);
		
		assertTrue(ret);
	}
}
