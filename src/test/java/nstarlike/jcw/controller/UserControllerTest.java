package nstarlike.jcw.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import nstarlike.jcw.controller.UserController;
import nstarlike.jcw.model.User;
import nstarlike.jcw.security.UserPrincipal;
import nstarlike.jcw.service.UserService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {
	"classpath:datasource-context.xml", 
	"classpath:security-context.xml", 
	"file:src/main/webapp/WEB-INF/app-context.xml"
})
@Transactional
class UserControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);
	
	private MockMvc mockMvc;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@Mock
	private UserService userService;
	
	@InjectMocks
	private UserController userController;
	
	@BeforeEach
	public void beforeEach() {
		logger.debug("start beforeEach() in UserControllerTest");
		
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders
				.standaloneSetup(userController)
				.build();
		
		setSession();
	}
	
	private void setSession() {
		logger.debug("start UserControllerTest.setSession");
		
		User user = new User();
		user.setId(2L);
		user.setLoginId("test1");
		user.setPassword("password");
		user.setName("name1");
		user.setEmail("test1@naver.com");
		
		UserPrincipal userPrincipal = new UserPrincipal(user);
		
		logger.debug("userPrincipal=" + userPrincipal);
		
		Authentication authentication = Mockito.mock(Authentication.class);
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
		
		when(authentication.getPrincipal()).thenReturn(userPrincipal);
		
		logger.debug("authentication.getPrincipal()=" + authentication.getPrincipal());
	}
	
	@Test
	void testMypage() throws Exception{
		logger.debug("start testMypage() in UserControllerTest");
		
		mockMvc.perform(get("/user/mypage")).andExpect(status().isOk());
	}

	@Test
	void testUpdateProc() throws Exception {
		logger.debug("start testUpdateProc() in UserControllerTest");
		
		when(passwordEncoder.encode("password")).thenReturn("password");
		
		mockMvc.perform(post("/user/updateProc")
				.param("id", "2")
				.param("loginId", "mockito1")
				.param("password", "password")
				.param("name", "name1")
				.param("email", "mockito1@naver.com")
			).andExpect(status().isOk());
	}

	@Test
	void testRegister() throws Exception {
		logger.debug("start testRegister() in UserControllerTest");
		
		mockMvc.perform(get("/user/register")).andExpect(status().isOk());
	}

	@Test
	void testRegisterProc() throws Exception {
		logger.debug("start testRegisterProc() in UserControllerTest");
		
		when(passwordEncoder.encode("password")).thenReturn("password");
		
		mockMvc.perform(post("/user/registerProc")
				.param("loginId", "mockito1")
				.param("password", "password")
				.param("name", "name1")
				.param("email", "mockito1@naver.com")
			).andExpect(status().isOk());
	}

	@Test
	void testUnregisterProc() throws Exception {
		logger.debug("start testUnregisterProc() in UserControllerTest");
		
		when(userService.delete(2L)).thenReturn(1);
		
		mockMvc.perform(post("/user/unregisterProc")).andExpect(status().isOk());
	}

}
