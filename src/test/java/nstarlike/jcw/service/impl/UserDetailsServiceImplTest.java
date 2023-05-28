package nstarlike.jcw.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import nstarlike.jcw.security.UserPrincipal;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {
	"classpath:datasource-context.xml", 
	"classpath:security-context.xml", 
	"classpath:aop-context.xml", 
	"file:src/main/webapp/WEB-INF/app-context.xml"
})
public class UserDetailsServiceImplTest {
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImplTest.class);
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Test
	public void testLoadUserByUsername() {
		logger.debug("start " + this.getClass().getSimpleName() + ".testLoadUserByUsername()");
		
		String username = "test1";
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		
		assertNotNull(userDetails);
		assertTrue(userDetails instanceof UserPrincipal);
		
		logger.debug("userDetails=" + userDetails);
	}
	
	
}
